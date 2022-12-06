package com.utils;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class ImportStoreImage {
    public static void main(String[] args) {
        File imagesDsc = new File("src/main/java/com/utils/storeImages");

        String sql = "update STORE set STORE_PIC=(?) where STORE_ID=?";

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = JDBCUtils.getConnection();
            ps = conn.prepareStatement(sql);
            int successRow = 0;

            for (int i = 1; i <= 9; i++) {
                String imageName = i + ".jpg";
                File image = new File(imagesDsc, imageName);
                try (FileInputStream fis = new FileInputStream(image)) {
                    ps.setBinaryStream(1, fis, fis.available());
                    ps.setInt(2, i);
                    successRow += ps.executeUpdate();
                }
            }
            System.out.println("成功上傳" + successRow + "張圖片");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.close(null, ps, conn);
        }

    }

}
