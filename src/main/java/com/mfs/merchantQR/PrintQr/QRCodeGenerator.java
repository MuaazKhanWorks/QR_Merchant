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

public class QRCodeGenerator {
    public static byte[] generateQRCode(String data) {
        try {
            // Use ZXing library to generate QR code image
            MultiFormatWriter writer = new MultiFormatWriter();
            BitMatrix bitMatrix = writer.encode(data, BarcodeFormat.QR_CODE, 200, 200);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);

            return outputStream.toByteArray();
        } catch (WriterException | IOException e) {
            e.printStackTrace(); // Handle exceptions appropriately
            return new byte[0];  // Return an empty byte array in case of an error
        }
    }

}