/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package HoaDon;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 *
 * @author thanh
 */
public class FrmHoaDon extends javax.swing.JFrame {

	/**
	 * Creates new form frmHoaDon
	 */
	public FrmHoaDon() {
		initComponents();
                setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);
	}

	/**
	 * This method is called from within the constructor to initialize the
	 * form. WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                btn_Xoa = new javax.swing.JButton();
                btn_Sua = new javax.swing.JButton();
                jScrollPane2 = new javax.swing.JScrollPane();
                table_Output = new javax.swing.JTable();
                jLabel4 = new javax.swing.JLabel();
                cbx_Sort = new javax.swing.JComboBox<>();
                jLabel1 = new javax.swing.JLabel();
                jLabel2 = new javax.swing.JLabel();
                jLabel3 = new javax.swing.JLabel();
                tf_MaKH = new javax.swing.JTextField();
                tf_MaHD = new javax.swing.JTextField();
                btn_Luu = new javax.swing.JButton();
                btn_Doc = new javax.swing.JButton();
                btn_Them = new javax.swing.JButton();
                tf_MaSB = new javax.swing.JTextField();
                jLabel5 = new javax.swing.JLabel();
                jLabel6 = new javax.swing.JLabel();
                tf_MaNV = new javax.swing.JTextField();

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

                btn_Xoa.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
                btn_Xoa.setText("Xoá");

                btn_Sua.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
                btn_Sua.setText("Sửa");

                table_Output.setModel(new javax.swing.table.DefaultTableModel(
                        new Object [][] {

                        },
                        new String [] {
                                "Mã Loại Sân", "Mã Khách Hàng", "Tên Khách Hàng", "Mã Nhân Viên", "Tên Nhân Viên", "Mã Sân Banh"
                        }
                ) {
                        Class[] types = new Class [] {
                                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
                        };
                        boolean[] canEdit = new boolean [] {
                                false, false, false, false, false, false
                        };

                        public Class getColumnClass(int columnIndex) {
                                return types [columnIndex];
                        }

                        public boolean isCellEditable(int rowIndex, int columnIndex) {
                                return canEdit [columnIndex];
                        }
                });
                jScrollPane2.setViewportView(table_Output);

                jLabel4.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
                jLabel4.setText("Tiêu chí sắp xếp:");

                cbx_Sort.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
                cbx_Sort.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã Loại Sân", "Mã Khách Hàng", "Tên Khách Hàng", "Mã Nhân Viên ", "Tên Nhân Viên", "Mã Sân Banh" }));

                jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
                jLabel1.setForeground(new java.awt.Color(153, 255, 255));
                jLabel1.setText("Quản Lý Hoá Đơn");

                jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
                jLabel2.setText("Mã Hoá Đơn:");

                jLabel3.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
                jLabel3.setText("Mã Khách Hàng:");

                tf_MaKH.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

                tf_MaHD.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

                btn_Luu.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
                btn_Luu.setText("Lưu");

                btn_Doc.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
                btn_Doc.setText("Đọc");

                btn_Them.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
                btn_Them.setText("Thêm");

                tf_MaSB.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

                jLabel5.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
                jLabel5.setText("Mã Nhân Viên:");

                jLabel6.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
                jLabel6.setText("Mã Sân Banh:");

                tf_MaNV.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel2)
                                                        .addComponent(jLabel3))
                                                .addGap(49, 49, 49)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(tf_MaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(tf_MaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(btn_Them)
                                                .addGap(18, 18, 18)
                                                .addComponent(btn_Xoa)
                                                .addGap(18, 18, 18)
                                                .addComponent(btn_Sua)
                                                .addGap(18, 18, 18)
                                                .addComponent(btn_Luu)
                                                .addGap(18, 18, 18)
                                                .addComponent(btn_Doc))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel5)
                                                        .addComponent(jLabel6))
                                                .addGap(62, 62, 62)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(tf_MaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(tf_MaSB, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel4)
                                                .addGap(31, 31, 31)
                                                .addComponent(cbx_Sort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 701, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(26, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(483, 483, 483)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel2)
                                                        .addComponent(tf_MaHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel3)
                                                        .addComponent(tf_MaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel5)
                                                        .addComponent(tf_MaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel6)
                                                        .addComponent(tf_MaSB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(btn_Them)
                                                .addComponent(btn_Xoa)
                                                .addComponent(btn_Sua)
                                                .addComponent(btn_Luu)
                                                .addComponent(btn_Doc))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel4)
                                                .addComponent(cbx_Sort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(65, Short.MAX_VALUE))
                );

                pack();
        }// </editor-fold>//GEN-END:initComponents

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
		/* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(FrmHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(FrmHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(FrmHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(FrmHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>
		//</editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new FrmHoaDon().setVisible(true);
			}
		});
	}

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JButton btn_Doc;
        private javax.swing.JButton btn_Luu;
        private javax.swing.JButton btn_Sua;
        private javax.swing.JButton btn_Them;
        private javax.swing.JButton btn_Xoa;
        private javax.swing.JComboBox<String> cbx_Sort;
        private javax.swing.JLabel jLabel1;
        private javax.swing.JLabel jLabel2;
        private javax.swing.JLabel jLabel3;
        private javax.swing.JLabel jLabel4;
        private javax.swing.JLabel jLabel5;
        private javax.swing.JLabel jLabel6;
        private javax.swing.JScrollPane jScrollPane2;
        private javax.swing.JTable table_Output;
        private javax.swing.JTextField tf_MaHD;
        private javax.swing.JTextField tf_MaKH;
        private javax.swing.JTextField tf_MaNV;
        private javax.swing.JTextField tf_MaSB;
        // End of variables declaration//GEN-END:variables

	public JButton getBtn_Doc() {
		return btn_Doc;
	}

	public JButton getBtn_Luu() {
		return btn_Luu;
	}

	public JButton getBtn_Sua() {
		return btn_Sua;
	}

	public JButton getBtn_Them() {
		return btn_Them;
	}

	public JButton getBtn_Xoa() {
		return btn_Xoa;
	}

	public JComboBox<String> getCbx_Sort() {
		return cbx_Sort;
	}

	public JTable getTable_Output() {
		return table_Output;
	}

	public JTextField getTf_MaHD() {
		return tf_MaHD;
	}

	public JTextField getTf_MaKH() {
		return tf_MaKH;
	}

	public JTextField getTf_MaNV() {
		return tf_MaNV;
	}

	public JTextField getTf_MaSB() {
		return tf_MaSB;
	}
}
