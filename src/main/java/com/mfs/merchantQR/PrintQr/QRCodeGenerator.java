/*Author Name:ahmad.raza
Project Name: merchantQR
Package Name:com.mfs.merchantQR.PrintQr
Class Name: BarcodeGenerator
Date and Time:12/13/2023 11:40 AM
Version:1.0*/
package com.mfs.merchantQR.PrintQr;


import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

public class QRCodeGenerator {

    public static String generateQRCode(String data) {
        try {
            // Use ZXing library to generate QR code image
            MultiFormatWriter writer = new MultiFormatWriter();
            BitMatrix bitMatrix = writer.encode(data, BarcodeFormat.QR_CODE, 200, 200);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);

            // Convert the byte array to Base64
            byte[] qrCodeImageBytes = outputStream.toByteArray();
            return Base64.getEncoder().encodeToString(qrCodeImageBytes);
        } catch (WriterException | IOException e) {
            e.printStackTrace(); // Handle exceptions appropriately
            return "";  // Return an empty string in case of an error
        }
    }


}