package id.ac.unpas.pp2_c_233040092.modul05;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

/**
 *
 * @author wdgus
 */
public class tugas {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame("Contoh BorderLayout");
                frame.setSize(400, 300);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                JLabel label = new JLabel("Label ada di Atas (NORTH)");
                JButton btnSouth = new JButton("Tombol ada di Bawah (SOUTH)");
                JButton btnWest = new JButton("WEST");
                JButton btnEast = new JButton("EAST");
                JButton btnCenter = new JButton("CENTER");

                // Tambahkan aksi ke setiap tombol
                btnSouth.addActionListener(e -> label.setText("Tombol di SOUTH diklik!"));
                btnWest.addActionListener(e -> label.setText("Tombolooo di WEST diklik!"));
                btnEast.addActionListener(e -> label.setText("Tombol di EAST diklik!"));
                btnCenter.addActionListener(e -> label.setText("Tombol di CENTER diklik!"));

                frame.add(label, BorderLayout.NORTH);
                frame.add(btnSouth, BorderLayout.SOUTH);
                frame.add(btnWest, BorderLayout.WEST);
                frame.add(btnEast, BorderLayout.EAST);
                frame.add(btnCenter, BorderLayout.CENTER);

                frame.setVisible(true);
            }
        });
    }
}
