package src.main.java.view;
import javax.swing.*;

import src.main.java.model.*;
import src.main.java.abstractClass.*;

import java.awt.*;

public class VueFin extends AbstractPanel {

    VueMain main;

    public VueFin(JFrame j, VueMain main) {
        super(j);
        this.main=main;
    }

    public void partieFini(int Gagnant) {
        JButton menu = new JButton("Menu");
        setVisible(false);
        JPanel fin = new JPanel();
        fin.setPreferredSize(new Dimension(j.getWidth(),j.getHeight()));
        fin.setLayout(null);
        JLabel finT = new JLabel();
        finT.setBounds(j.getWidth()/2-200, j.getHeight()/2-250, 500, 500);
        if(Gagnant!=0) {
            finT.setText("Joueur " + Gagnant + " à gagné");
            finT.setFont(new Font("Arial", 0 , 50));
            fin.add(finT,BorderLayout.CENTER);
        } else {
            finT.setText("Égalité");
            finT.setFont(new Font("Arial", 0 , 50));
            finT.setBounds(j.getWidth()/2-100, j.getHeight()/2-250, 500, 500);
            fin.add(finT,BorderLayout.CENTER);
        }
        main.dispose();
        menu.setBounds(j.getWidth()/2-100, j.getHeight()/2+150, 100, 50);
        menu.addActionListener(e-> {
            j.dispose();
            VueGenerale g = new VueGenerale();
        });
        fin.add(menu, BorderLayout.SOUTH);
        j.setContentPane(fin);
    }

}
