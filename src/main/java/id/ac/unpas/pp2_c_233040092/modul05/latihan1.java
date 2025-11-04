/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.pp2_c_233040092.modul05;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import java.awt.FlowLayout;


/**
 *
 * @author Rianda Rafqi
 */
public class latihan1 {
    public static void main (String []args){
        SwingUtilities.invokeLater(new Runnable (){
            public void run(){
                JFrame frame = new JFrame("INI BINGKAI");
                frame.setSize(400,300);
                frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
                
                
                frame.setLayout(new BorderLayout());
                
                
                JLabel label = new JLabel("Label ada diatas (NORTH)");
                JButton button = new JButton ("Tombol ada dibawah (SOUTH)");
                
                button.addActionListener(e -> {
                    label.setText("Tombol di SOUTH diklik!");
                });
                
                frame.add(label, BorderLayout.NORTH);
                frame.add(button, BorderLayout.SOUTH);
                frame.add(new JButton("WEST"), BorderLayout.WEST);
                frame.add(new JButton("EAST"), BorderLayout.EAST);
                frame.add(new JButton("CENTER"), BorderLayout.CENTER);
        
                frame.setVisible(true);
            }
        });
    }
}