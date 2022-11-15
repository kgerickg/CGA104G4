package com.utils;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class Importimage {
	public static void main(String[] args) {
		File imagedsc = new File("CGA104G4/src/main/java/com/utils/images");

		String sql = "update MEMBER set MEM_PIC=(?) where MEM_ID=?";

		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = JDBCUtils.getConnection();
			ps = conn.prepareStatement(sql);
			int sussessrow = 0;

			for (int i = 1; i <= 10; i++) {
				String imageName = i + ".png";
				File image = new File(imagedsc, imageName);
				FileInputStream fis = null;
				try {
					fis = new FileInputStream(image);
					ps.setBinaryStream(1, fis, fis.available());
					ps.setInt(2, i);
					sussessrow += ps.executeUpdate();
				} finally {
					if (fis != null) {
						fis.close();
					}
				}
			}
			System.out.println(sussessrow);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(null, ps, conn);
		}

	}

}
