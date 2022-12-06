package com.photo.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertPhotosIntoDB {
	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/FOOD?serverTimezone=Asia/Taipei";
	public static final String USER = "root";
	public static final String PASSWORD = "password";

	private static final String INSERT_STMT = "insert into PHOTO (PROD_ID, PHOTO_STAT, PHOTO_PIC) values (?, ?, ?)";

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, 1);
			pstmt.setInt(2, 1);
			byte[] pic1 = getPictureByteArray("C:/CGA104G4_Workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/CGA104G4/resources/images/PROD_ID_1.jpg");
//			byte[] pic1 = getPictureByteArray("C:/Users/kgerickg/Desktop/CGA104G4new/CGA104G4/target/CGA104G4/resources/images/PROD_ID_1.jpg");
			pstmt.setBytes(3, pic1);
			pstmt.executeUpdate();
			
			pstmt.setInt(1, 2);
			pstmt.setInt(2, 1);
			byte[] pic2 = getPictureByteArray("C:/CGA104G4_Workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/CGA104G4/resources/images/PROD_ID_2.jpg");
//			byte[] pic2 = getPictureByteArray("C:/Users/kgerickg/Desktop/CGA104G4new/CGA104G4/target/CGA104G4/resources/images/PROD_ID_2.jpg");
			pstmt.setBytes(3, pic2);
			pstmt.executeUpdate();
			
			pstmt.setInt(1, 3);
			pstmt.setInt(2, 1);
			byte[] pic3 = getPictureByteArray("C:/CGA104G4_Workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/CGA104G4/resources/images/PROD_ID_3.jpg");
//			byte[] pic3 = getPictureByteArray("C:/Users/kgerickg/Desktop/CGA104G4new/CGA104G4/target/CGA104G4/resources/images/PROD_ID_3.jpg");
			pstmt.setBytes(3, pic3);
			pstmt.executeUpdate();
			
			pstmt.setInt(1, 4);
			pstmt.setInt(2, 1);
			byte[] pic4 = getPictureByteArray("C:/CGA104G4_Workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/CGA104G4/resources/images/PROD_ID_4.jpg");
//			byte[] pic4 = getPictureByteArray("C:/Users/kgerickg/Desktop/CGA104G4new/CGA104G4/target/CGA104G4/resources/images/PROD_ID_4.jpg");
			pstmt.setBytes(3, pic4);
			pstmt.executeUpdate();
			
			pstmt.setInt(1, 5);
			pstmt.setInt(2, 1);
			byte[] pic5 = getPictureByteArray("C:/CGA104G4_Workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/CGA104G4/resources/images/PROD_ID_5.jpg");
//			byte[] pic5 = getPictureByteArray("C:/Users/kgerickg/Desktop/CGA104G4new/CGA104G4/target/CGA104G4/resources/images/PROD_ID_5.jpg");
			pstmt.setBytes(3, pic5);
			pstmt.executeUpdate();
			
			pstmt.setInt(1, 6);
			pstmt.setInt(2, 1);
			byte[] pic6 = getPictureByteArray("C:/CGA104G4_Workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/CGA104G4/resources/images/PROD_ID_6.jpg");
//			byte[] pic6 = getPictureByteArray("C:/Users/kgerickg/Desktop/CGA104G4new/CGA104G4/target/CGA104G4/resources/images/PROD_ID_6.jpg");
			pstmt.setBytes(3, pic6);
			pstmt.executeUpdate();
			
			pstmt.setInt(1, 7);
			pstmt.setInt(2, 1);
			byte[] pic7 = getPictureByteArray("C:/CGA104G4_Workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/CGA104G4/resources/images/PROD_ID_7.jpg");
//			byte[] pic7 = getPictureByteArray("C:/Users/kgerickg/Desktop/CGA104G4new/CGA104G4/target/CGA104G4/resources/images/PROD_ID_7.jpg");
			pstmt.setBytes(3, pic7);
			pstmt.executeUpdate();
			
			pstmt.setInt(1, 8);
			pstmt.setInt(2, 1);

			byte[] pic8 = getPictureByteArray("C:/CGA104G4_Workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/CGA104G4/resources/images/PROD_ID_8.jpg");
//			byte[] pic8 = getPictureByteArray("C:/Users/kgerickg/Desktop/CGA104G4new/CGA104G4/target/CGA104G4/resources/images/PROD_ID_8.jpg");

			pstmt.setBytes(3, pic8);
			pstmt.executeUpdate();
			
			pstmt.setInt(1, 9);
			pstmt.setInt(2, 1);
			byte[] pic9 = getPictureByteArray("C:/CGA104G4_Workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/CGA104G4/resources/images/PROD_ID_9.jpg");
//			byte[] pic9 = getPictureByteArray("C:/Users/kgerickg/Desktop/CGA104G4new/CGA104G4/target/CGA104G4/resources/images/PROD_ID_9.jpg");

			pstmt.setBytes(3, pic9);
			pstmt.executeUpdate();
			
			pstmt.setInt(1, 10);
			pstmt.setInt(2, 1);
//			byte[] pic10 = getPictureByteArray("C:/Users/kgerickg/Desktop/CGA104G4new/CGA104G4/target/CGA104G4/resources/images/PROD_ID_10.jpg");
			byte[] pic10 = getPictureByteArray("C:/CGA104G4_Workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/CGA104G4/resources/images/PROD_ID_10.jpg");
			pstmt.setBytes(3, pic10);
			pstmt.executeUpdate();
			
			pstmt.setInt(1, 11);
			pstmt.setInt(2, 1);
//			byte[] pic11 = getPictureByteArray("C:/Users/kgerickg/Desktop/CGA104G4new/CGA104G4/target/CGA104G4/resources/images/PROD_ID_11.jpg");
			byte[] pic11 = getPictureByteArray("C:/CGA104G4_Workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/CGA104G4/resources/images/PROD_ID_11.jpg");
			pstmt.setBytes(3, pic11);
			pstmt.executeUpdate();
			
			pstmt.setInt(1, 12);
			pstmt.setInt(2, 1);
//			byte[] pic12 = getPictureByteArray("C:/Users/kgerickg/Desktop/CGA104G4new/CGA104G4/target/CGA104G4/resources/images/PROD_ID_12.jpg");
			byte[] pic12 = getPictureByteArray("C:/CGA104G4_Workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/CGA104G4/resources/images/PROD_ID_12.jpg");
			pstmt.setBytes(3, pic12);
			pstmt.executeUpdate();
			
			pstmt.setInt(1, 13);
			pstmt.setInt(2, 1);
//			byte[] pic13 = getPictureByteArray("C:/Users/kgerickg/Desktop/CGA104G4new/CGA104G4/target/CGA104G4/resources/images/PROD_ID_13.jpg");
			byte[] pic13 = getPictureByteArray("C:/CGA104G4_Workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/CGA104G4/resources/images/PROD_ID_13.jpg");
			pstmt.setBytes(3, pic13);
			pstmt.executeUpdate();
			
			pstmt.setInt(1, 14);
			pstmt.setInt(2, 1);
//			byte[] pic14 = getPictureByteArray("C:/Users/kgerickg/Desktop/CGA104G4new/CGA104G4/target/CGA104G4/resources/images/PROD_ID_14.jpg");
			byte[] pic14 = getPictureByteArray("C:/CGA104G4_Workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/CGA104G4/resources/images/PROD_ID_14.jpg");
			pstmt.setBytes(3, pic14);
			pstmt.executeUpdate();
			
			pstmt.setInt(1, 15);
			pstmt.setInt(2, 1);
//			byte[] pic15 = getPictureByteArray("C:/Users/kgerickg/Desktop/CGA104G4new/CGA104G4/target/CGA104G4/resources/images/PROD_ID_15.jpg");
			byte[] pic15 = getPictureByteArray("C:/CGA104G4_Workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/CGA104G4/resources/images/PROD_ID_15.jpg");
			pstmt.setBytes(3, pic15);
			pstmt.executeUpdate();
			
			pstmt.setInt(1, 16);
			pstmt.setInt(2, 1);
//			byte[] pic16 = getPictureByteArray("C:/Users/kgerickg/Desktop/CGA104G4new/CGA104G4/target/CGA104G4/resources/images/PROD_ID_16.jpg");
			byte[] pic16 = getPictureByteArray("C:/CGA104G4_Workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/CGA104G4/resources/images/PROD_ID_16.jpg");
			pstmt.setBytes(3, pic16);
			pstmt.executeUpdate();
			
			pstmt.setInt(1, 17);
			pstmt.setInt(2, 1);
//			byte[] pic17 = getPictureByteArray("C:/Users/kgerickg/Desktop/CGA104G4new/CGA104G4/target/CGA104G4/resources/images/PROD_ID_10.jpg");
			byte[] pic17 = getPictureByteArray("C:/CGA104G4_Workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/CGA104G4/resources/images/PROD_ID_17.jpg");
			pstmt.setBytes(3, pic17);
			pstmt.executeUpdate();
			
			pstmt.setInt(1, 18);
			pstmt.setInt(2, 1);
//			byte[] pic18 = getPictureByteArray("C:/Users/kgerickg/Desktop/CGA104G4new/CGA104G4/target/CGA104G4/resources/images/PROD_ID_18.jpg");
			byte[] pic18 = getPictureByteArray("C:/CGA104G4_Workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/CGA104G4/resources/images/PROD_ID_18.jpg");
			pstmt.setBytes(3, pic18);
			pstmt.executeUpdate();
			
			pstmt.setInt(1, 19);
			pstmt.setInt(2, 1);
//			byte[] pic19 = getPictureByteArray("C:/Users/kgerickg/Desktop/CGA104G4new/CGA104G4/target/CGA104G4/resources/images/PROD_ID_19.jpg");
			byte[] pic19 = getPictureByteArray("C:/CGA104G4_Workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/CGA104G4/resources/images/PROD_ID_19.jpg");
			pstmt.setBytes(3, pic19);
			pstmt.executeUpdate();
			
			pstmt.setInt(1, 20);
			pstmt.setInt(2, 1);
//			byte[] pic20 = getPictureByteArray("C:/Users/kgerickg/Desktop/CGA104G4new/CGA104G4/target/CGA104G4/resources/images/PROD_ID_20.jpg");
			byte[] pic20 = getPictureByteArray("C:/CGA104G4_Workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/CGA104G4/resources/images/PROD_ID_20.jpg");
			pstmt.setBytes(3, pic20);
			pstmt.executeUpdate();

			System.out.println("新增成功");

		} catch (ClassNotFoundException ce) {
			System.out.println(ce);
		} catch (SQLException se) {
			System.out.println(se);
		} catch (IOException ie) {
			System.out.println(ie);
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					System.out.println(se);
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					System.out.println(se);
				}
			}
		}
	}

	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}
}