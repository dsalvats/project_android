package com.xpomanager.utils;

import android.graphics.Bitmap;
import android.graphics.Color;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.util.HashMap;
import java.util.Map;

public class QR {

    /***********************
     * CONSTANTES GLOBALES *
     ***********************/
    private final static int DEFAULT_BITMAP_WIDTH = 200;
    private final static int DEFAULT_HINT_MARGIN = 1;

    /***********
     * MÉTODOS *
     ***********/
    /**
     * Dado un string, devuelve un Bitmap con el QR de la string transformado.
     * @param str: Cadena de caracteres que contiende la url a redireccionar.
     * @return Bitmap que contiene la String proporcionada
     */
    public static Bitmap encodeAsBitmap(String str) {
        return encodeAsBitmap(str, DEFAULT_BITMAP_WIDTH);
    }

    /**
     * Dado un string y un tamaño de lado, devuelve un Bitmap con el QR de la string transformado.
     * Source: https://stackoverflow.com/a/30529128
     * @param str: Cadena de caracteres que contiende la url a redireccionar.
     * @param width Tamaño de lado que tendrá el qr
     * @return Bitmap que contiene la String proporcionada
     */
    public static Bitmap encodeAsBitmap(String str, int width) {
        BitMatrix result;

        // Source: https://stackoverflow.com/a/50073024
        // Reduce el margen del QR
        Map<EncodeHintType, Object> hintMap = new HashMap<>();
        hintMap.put(EncodeHintType.MARGIN, DEFAULT_HINT_MARGIN);

        try {
            result = new MultiFormatWriter().encode(str,
                    BarcodeFormat.QR_CODE, width, width, hintMap);
        } catch (IllegalArgumentException | WriterException iae) {
            // Unsupported format
            return null;
        }

        int w = result.getWidth();
        int h = result.getHeight();
        int[] pixels = new int[w * h];

        for (int y = 0; y < h; y++) {
            int offset = y * w;
            for (int x = 0; x < w; x++) {
                pixels[offset + x] = result.get(x, y) ? Color.BLACK : Color.WHITE;
            }
        }

        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, width, 0, 0, w, h);

        return bitmap;
    }

}
