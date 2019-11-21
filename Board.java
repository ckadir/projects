
package odev4;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Scanner;

/**
 *
 * @author ckadir
 */
public class Board extends Applet {

    list path;
    int w = 100;
    int h = 100;

    @Override
    public void init() {
        super.init(); //To change body of generated methods, choose Tools | Templates.
        this.setSize(w * 10, h * 10);
        /**
         * Veriler giriliyor
         *
         */
        Scanner k = new Scanner(System.in);
        int x1, y1, x2, y2;
        x1 = k.nextInt();
        y1 = k.nextInt();
        x2 = k.nextInt();
        y2 = k.nextInt();

        node konum = new node(x1, y1, 0);
        node hedef = new node(x2, y2, 0);
        search s = new search(konum, hedef, 8); // 3. parametre tahtanın boyutu 8x8
        this.path = s.path;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.

        board(g);

    }

    public void board(Graphics g) {

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i == 0) {
                    g.setColor(Color.black);
                    g.drawString((j) + "", 40 + j * 50, 15 + i * 50);
                    g.drawString((j) + "", 5 + i * 50, 50 + j * 50);
                    g.setColor(Color.white);
                }

                if (((i + j) % 2) == 0) {
                    g.setColor(Color.black);
                } else {
                    g.setColor(Color.white);
                }

                g.fillRect(20 + i * 50, 20 + j * 50, 50, 50);
            }

        }
        int k = 1;

        node2 tmp = path.get();
        g.setColor(Color.green);
        g.fillRect(20 + tmp.x * 50, 20 + tmp.y * 50, 50, 50);
        g.setColor(Color.blue);
        g.drawString("BAŞLA", 25 + tmp.x * 50, 50 + tmp.y * 50);

        while (!path.isNull(tmp.next)) {
            tmp = path.get();
            g.setColor(Color.LIGHT_GRAY);
            g.fillOval(30 + tmp.x * 50, 30 + tmp.y * 50, 30, 30);
            g.setColor(Color.blue);

            g.drawString(k + "", 40 + tmp.x * 50, 50 + tmp.y * 50);
            g.setColor(Color.white);
            k++;
        }
    }
}