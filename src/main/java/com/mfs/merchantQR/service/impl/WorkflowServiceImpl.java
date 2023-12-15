package com.mfs.merchantQR.service.impl;

import com.mfs.merchantQR.dto.*;
import com.mfs.merchantQR.model.TblResponseMessage;
import com.mfs.merchantQR.repo.TblResponseMessageRepo;
import com.mfs.merchantQR.service.WorkflowService;
import com.mfs.merchantQR.utils.Constants;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class WorkflowServiceImpl implements WorkflowService {

    @PersistenceContext
    EntityManager em;

    @Autowired
    private TblResponseMessageRepo tblResponseMessageRepo;

    @Override
    public ProcedureResponse checkMcApplicability(String tableName, String formName, String requestType) {
        ProcedureResponse response = new ProcedureResponse();

        try {
            Session session = em.unwrap(Session.class);
            session.doWork(connection -> {
                try (CallableStatement call = connection.prepareCall("{call CHECK_MC_APPLICABLE(?,?,?) }")) {
                    call.setString(1, tableName);
                    call.setString(2, formName);
                    call.setString(3, requestType);

                    try (ResultSet rs = call.executeQuery()) {
                        if (rs.next()) {
                            response.setMcApplicability(rs.getInt(Constants.APPLICABILITY));
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }


    @Override
    public McResponse parkRequestToChecker(String formName, String makerComments, String tableName, String requestType, String updateType, String refTableId, String updateJson, BigDecimal userId) {
        McResponse response = new McResponse();

        try {
            Session session = em.unwrap(Session.class);

            session.doWork(connection -> {
                try (CallableStatement call = connection.prepareCall("{call MC_REQUEST(?,?,?,?,?,?,?,?)}")) {
                    call.setString(1, formName);
                    call.setBigDecimal(2, userId);
                    call.setString(3, makerComments);
                    call.setString(4, tableName);
                    call.setString(5, requestType);
                    call.setString(6, updateType);
                    call.setString(7, updateJson);
                    call.setBigDecimal(8, new BigDecimal(refTableId));

                    try (ResultSet rs = call.executeQuery()) {
                        if (rs.next()) {
                            response.setStatus(rs.getInt(Constants.STATUS));
                            response.setStatusDecsr(rs.getString(Constants.STATUS_DESCR));
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }


    @Override
    public McActionResponse mcAction(McActionRequest mcActionRequest) {
        McActionResponse response = new McActionResponse();

        try {
            Session session = em.unwrap(Session.class);
            session.doWork(connection -> {
                try (CallableStatement call = connection.prepareCall("{call MC_ACTION(?,?,?,?,?)}")) {
                    call.setInt(1, Integer.valueOf(mcActionRequest.getMcRequestId()));
                    call.setInt(2, Integer.valueOf(mcActionRequest.getMcPeindingRequestId()));
                    call.setInt(3, Integer.valueOf(mcActionRequest.getCheckerId().toString()));
                    call.setString(4, mcActionRequest.getCheckerComments());
                    call.setString(5, mcActionRequest.getAction());

                    try (ResultSet rs = call.executeQuery()) {
                        if (rs.next()) {
                            response.setRequestStatus(rs.getString(Constants.REQUEST_STATUS));
                            response.setStatus(rs.getInt(Constants.STATUS));
                            response.setStatusDecsr(rs.getString(Constants.STATUS_DESCR));
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return response;
    }


    @Override
    public TblResponseMessage getResponseMessageByResponseDescr(String descr) {
        return tblResponseMessageRepo.findByResponseMessageDescr(descr);
    }

    @Override
    public List<CheckerSearchResponse> getallmyauthorizationsrequests(CheckerSearch checkerSearch, BigDecimal userId) throws SQLException {
        String dateFromInput = Constants.unhandleException;
        String dateToInput = Constants.unhandleException;
        if (checkerSearch.getFromDate() != null && !(checkerSearch.getFromDate().equals(Constants.EMPTY))) {
            dateFromInput = checkerSearch.getFromDate() + " 00:00:00";
        }
        if (checkerSearch.getToDate() != null && !(checkerSearch.getToDate().equals(Constants.EMPTY))) {
            dateToInput = checkerSearch.getToDate() + " 23:59:59";
        }
        List<CheckerSearchResponse> checkerSearchResponseArrayList = new ArrayList<>();
        String sql = "SELECT R.MC_REQUEST_ID, R.REF_TABLE_ID, R.ACTION_ID, R.FORM_NAME, U.NAME, P.CREATEDATE AS MAKE_DATE, COALESCE(S.STATUS_DESCR, SP.STATUS_DESCR) AS STATUS_DESCR, R.TABLE_NAME, R.REQUEST_TYPE,\n" +
                "    CASE \n" +
                "    WHEN R.UPDATE_TYPE = 'I' OR R.REQUEST_TYPE = 'I' THEN 'Create Request'\n" +
                "    WHEN R.UPDATE_TYPE = 'U' OR R.REQUEST_TYPE = 'U' THEN 'Update Request'\n" +
                "    WHEN R.UPDATE_TYPE = 'A' OR R.REQUEST_TYPE = 'A' THEN 'Enable/Disable Request'\n" +
                "    ELSE NULL\n" +
                "END AS REQUEST_TYPE_DESCR," +
                "    P.MC_PENDING_REQUEST_ID, A.CHECKER_COMMENTS, G.VIEW_DETAIL_URL, G.EDIT_DETAIL_URL, P.ACTION_TAKEN, P.SEQ,R.UPDATE_TYPE\n" +
                "FROM tbl_mc_request R\n" +
                "INNER JOIN tbl_mc_pending_request P ON R.MC_REQUEST_ID = P.MC_REQUEST_ID\n" +
                "INNER JOIN lkp_status SP ON R.STATUS_ID = SP.STATUS_ID\n" +
                "INNER JOIN tbl_mc_config G ON R.MC_CONFIG_ID = G.MC_CONFIG_ID\n" +
                "INNER JOIN tbl_user U ON R.MAKER_ID = U.USER_ID\n" +
                "LEFT OUTER JOIN tbl_mc_request_action A ON P.MC_PENDING_REQUEST_ID = A.MC_PENDING_REQUEST_ID\n" +
                "LEFT JOIN lkp_status S ON A.STATUS_ID = S.STATUS_ID\n" +
                "WHERE R.ACTION_ID = COALESCE(NULLIF(?,''),R.ACTION_ID)\n" +
                "               AND R.FORM_NAME = COALESCE(NULLIF(?,''),R.FORM_NAME)\n" +
                "               AND DATE(R.CREATEDATE) BETWEEN COALESCE(NULLIF(?,''), DATE(R.CREATEDATE)) AND COALESCE(NULLIF(?,''), DATE(R.CREATEDATE))\n" +
                "\n" +
                "               AND COALESCE(A.STATUS_ID,R.STATUS_ID) = COALESCE(NULLIF(?,''),COALESCE(A.STATUS_ID,R.STATUS_ID))\n" +
                "               AND R.CREATEUSER = COALESCE(NULLIF(?,''),R.CREATEUSER)\n" +
                "               AND COALESCE(R.LASTUPDATEUSER,0) = COALESCE(NULLIF(?,''),COALESCE(R.LASTUPDATEUSER,0))\n" +
                "               AND P.USER_ID = ?\n" +
                "               ORDER BY P.MC_PENDING_REQUEST_ID";
        Query query = em.createNativeQuery(sql);
        query.setParameter(1, checkerSearch.getActionId());
        query.setParameter(2, checkerSearch.getFormName());
        query.setParameter(3, dateFromInput);
        query.setParameter(4, dateToInput);
        query.setParameter(5, checkerSearch.getStatusId());
        query.setParameter(6, checkerSearch.getCreatedBy());
        query.setParameter(7, checkerSearch.getUpdatedBy());
        query.setParameter(8, userId);
        List<Object> resultList = query.getResultList();
        if (resultList != null && !resultList.isEmpty()) {
            processQueryResult(checkerSearchResponseArrayList, resultList);
        }
        return checkerSearchResponseArrayList;
    }

    public void processQueryResult(List<CheckerSearchResponse> checkerSearchResponseArrayList, List<Object> resultList) {
        for (Object result : resultList) {
            CheckerSearchResponse checkerSearchResponse = new CheckerSearchResponse();
            Object[] row = (Object[]) result;

            for (int i = 0; i < row.length; i++) {
                if (row[i] != null) {
                    setFieldValue(checkerSearchResponse, i, row[i]);
                }
            }

            checkerSearchResponseArrayList.add(checkerSearchResponse);
        }
    }

    private void setFieldValue(CheckerSearchResponse checkerSearchResponse, int index, Object value) {
        switch (index) {
            case 0:
                checkerSearchResponse.setMcRequestId((Integer) value);
                break;
            case 1:
                checkerSearchResponse.setRefTableId((Integer) value);
                break;
            case 2:
                checkerSearchResponse.setActionId((Integer) value);
                break;
            case 3:
                checkerSearchResponse.setFormName((String) value);
                break;
            case 4:
                checkerSearchResponse.setUserName((String) value);
                break;
            case 5:
                checkerSearchResponse.setMakeDate((Date) value);
                break;
            case 6:
                checkerSearchResponse.setStatusDescr((String) value);
                break;
            case 7:
                checkerSearchResponse.setTableName((String) value);
                break;
            case 8:
                checkerSearchResponse.setRequestType((String) value);
                break;
            case 9:
                checkerSearchResponse.setRequestTypeDescr((String) value);
                break;
            case 10:
                checkerSearchResponse.setMcPendingRequest((Integer) value);
                break;
            case 11:
                checkerSearchResponse.setCheckerComments((String) value);
                break;
            case 12:
                checkerSearchResponse.setViewDetailUrl((String) value);
                break;
            case 13:
                checkerSearchResponse.setEditDetailUrl((String) value);
                break;
            case 14:
                checkerSearchResponse.setActionTaken((String) value);
                break;
            case 15:
                checkerSearchResponse.setSeq((Integer) value);
                break;
            case 16:
                checkerSearchResponse.setUpdateType((String) value);
                break;
            default:
                break;
        }
    }

    @Override
    public List<AuthorizationHistoryResponse> getActionAuthorizationHistory(String refTableId, String tableName) {
        List<AuthorizationHistoryResponse> authorizationHistoryResponses = new ArrayList<>();
        String sql = "SELECT \n" +
                "    P.SEQ AS ESCALATION_LEVEL, \n" +
                "    UA.NAME AS CHECKED_BY, \n" +
                "    DATE_FORMAT(A.CHECK_DATE, '%d/%m/%Y, %h:%i %p') AS CHECKED_ON, \n" +
                "    A.CHECKER_COMMENTS AS AUTHORIZER_COMMENTS,\n" +
                "    DATE_FORMAT(P.CREATEDATE, '%d/%m/%Y, %h:%i %p') AS INTIMATION_ON, \n" +
                "    COALESCE(SA.STATUS_NAME, SP.STATUS_NAME) AS AUTHORIZATION_STATUS,\n" +
                "    UP.NAME AS INTIMATION_TO\n" +
                "FROM \n" +
                "    tbl_mc_request R\n" +
                "    INNER JOIN tbl_mc_pending_request P ON R.mc_request_id = P.mc_request_id\n" +
                "    INNER JOIN lkp_status SP ON P.status_id = SP.status_id\n" +
                "    LEFT JOIN tbl_user UP ON P.user_id = UP.user_id\n" +
                "    LEFT JOIN tbl_mc_request_action A ON P.mc_pending_request_id = A.mc_pending_request_id\n" +
                "    LEFT JOIN lkp_status SA ON A.status_id = SA.status_id\n" +
                "    LEFT JOIN tbl_user UA ON A.checker_id = UA.user_id\n" +
                "WHERE \n" +
                "    R.ref_table_id = ?\n" +
                "    AND R.table_name = ?\n" +
                "ORDER BY \n" +
                "    P.mc_pending_request_id\n";
        Query query = em.createNativeQuery(sql);
        query.setParameter(1, refTableId);
        query.setParameter(2, tableName);
        List<Object> resultList = query.getResultList();
        if (resultList != null && !resultList.isEmpty()) {
            processHistoryQueryResult(authorizationHistoryResponses, resultList);

        }
        return authorizationHistoryResponses;
    }

    public void processHistoryQueryResult(List<AuthorizationHistoryResponse> authorizationHistoryResponses, List<Object> resultList) {
        for (Object result : resultList) {
            AuthorizationHistoryResponse authorizationHistoryResponse = new AuthorizationHistoryResponse();
            Object[] row = (Object[]) result;

            for (int i = 0; i < row.length; i++) {
                if (row[i] != null) {
                    setHistoryFieldValue(authorizationHistoryResponse, i, row[i]);
                }
            }

            authorizationHistoryResponses.add(authorizationHistoryResponse);
        }
    }

    private void setHistoryFieldValue(AuthorizationHistoryResponse authorizationHistoryResponse, int index, Object value) {
        switch (index) {
            case 0:
                authorizationHistoryResponse.setEscalationLevel((Integer) value);
                break;
            case 1:
                authorizationHistoryResponse.setCheckedBy((String) value);
                break;
            case 2:
                authorizationHistoryResponse.setCheckedOn((String) value);
                break;
            case 3:
                authorizationHistoryResponse.setAuthorizerComments((String) value);
                break;
            case 4:
                authorizationHistoryResponse.setIntimationOn((String) value);
                break;
            case 5:
                authorizationHistoryResponse.setAuthorizationStatus((String) value);
                break;
            case 6:
                authorizationHistoryResponse.setIntimationTo((String) value);
                break;
            default:
                break;
        }
    }


}