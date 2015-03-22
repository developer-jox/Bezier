/* 
 * Copyright (C) 2015 developer-jox <developer.jox@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package bezier;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.geom.CubicCurve2D;
import java.awt.geom.Point2D;
import java.util.Random;
import javax.swing.JPanel;

/**
 *
 * @author developer-jox <developer.jox@gmail.com>
 */
public class Bezier extends JPanel {

    static final int NRCURVES = 1;
    static final int NRPOINTS = 4 * NRCURVES;

    Point2D[] p = new Point2D.Double[NRPOINTS];
    double[] pntVx = new double[NRPOINTS];
    double[] pntVy = new double[NRPOINTS];

    CubicCurve2D q = new CubicCurve2D.Double();
    CubicCurve2D q2 = new CubicCurve2D.Double();

    public Bezier() {
//        Dimension res = Toolkit.getDefaultToolkit().getScreenSize(); 
//        setPreferredSize(new Dimension(res.width, res.height - 79));
        
        setPreferredSize(new Dimension(550, 550));
        setDoubleBuffered(true);

        p[0] = new Point2D.Double(210, 220);
        p[1] = new Point2D.Double(200, 100);
        p[2] = new Point2D.Double(200, 200);
        p[3] = new Point2D.Double(390, 250);
    }
//    public Point P(double t, Point A, Point B, Point C, Point D) {
//        double a = Math.pow(1 - t, 3);
//        double b = 3 * Math.pow(1 - t, 2) * t;
//        double c = 3 * (1 - t) * Math.pow(t, 2);
//        double d = Math.pow(t, 3);
//        double x = a * A.x + b * B.x + c * C.x + d * D.x;
//        double y = a * A.y + b * B.y + c * C.y + d * D.y;
//        return new Point((int) x, (int) y);
//    }

    public void run() {
        while (true) {
            for (int i = 0; i < pntVx.length; ++i) {
                if (Math.signum(pntVx[i]) >= 0.0) {
                    if (Math.random() >= 0.5 && pntVx[i] < 5) {
                        Random rnd = new Random();
                        pntVx[i] += rnd.nextDouble() + 0.01;
                    }
                    if (Math.random() < 0.5 && pntVx[i] > -5) {
                        Random rnd = new Random();
                        pntVx[i] -= rnd.nextDouble() + 0.01;
                    }
                }
                if (Math.signum(pntVx[i]) == -1.0) {
                    if (Math.random() >= 0.5 && pntVx[i] < 5) {
                        Random rnd = new Random();
                        pntVx[i] += rnd.nextDouble() + 0.01;
                    }
                    if (Math.random() < 0.5 && pntVx[i] > -5) {
                        Random rnd = new Random();
                        pntVx[i] -= rnd.nextDouble() + 0.01;
                    }
                }
            }
            for (int i = 0; i < pntVy.length; ++i) {
                if (Math.signum(pntVy[i]) >= 0.0) {
                    if (Math.random() >= 0.5 && pntVy[i] < 5) {
                        Random rnd = new Random();
                        pntVy[i] += rnd.nextDouble() + 0.01;
                    }
                    if (Math.random() < 0.5 && pntVy[i] > -5) {
                        Random rnd = new Random();
                        pntVy[i] -= rnd.nextDouble() + 0.01;
                    }
                }
                if (Math.signum(pntVy[i]) == -1.0) {
                    if (Math.random() >= 0.5 && pntVy[i] < 5) {
                        Random rnd = new Random();
                        pntVy[i] += rnd.nextDouble() + 0.01;
                    }
                    if (Math.random() < 0.5 && pntVy[i] > -5) {
                        Random rnd = new Random();
                        pntVy[i] -= rnd.nextDouble() + 0.01;
                    }
                }
            }
            for (int i = 0; i < p.length; i++) {
                if (p[i].getX() > 400) {
//                    System.out.println("X > 400");
                    p[i].setLocation(400, p[i].getY());
                    pntVx[i] = -pntVx[i];
                }
                if (p[i].getX() < 100) {
                    p[i].setLocation(100, p[i].getY());
//                    System.out.println("X < 100");
                    pntVx[i] = -pntVx[i];
                }
                if (p[i].getY() > 400) {
//                    System.out.println("Y > 100");
                    p[i].setLocation(p[i].getX(), 400);
                    pntVy[i] = -pntVy[i];
                }
                if (p[i].getY() < 100) {
                    p[i].setLocation(p[i].getX(), 100);
//                    System.out.println("Y < 100");
                    pntVy[i] = -pntVy[i];
                }
            }
            for (int i = 0; i < p.length; ++i) {
                p[i].setLocation(p[i].getX() + pntVx[i], p[i].getY() + pntVy[i]);
            }
            q.setCurve(p, 0);

            repaint();
            try {
                Thread.sleep(10);
            } catch (Exception e) {
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawRect(100, 100, 300, 300);
        g2.setStroke(new BasicStroke(0.5f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(Color.black);
        g2.draw(q);
        g2.fillRect((int) p[3].getX() - 5, (int) p[3].getY() - 5, 10, 10);
        g2.fillRect((int) p[0].getX() - 5, (int) p[0].getY() - 5, 10, 10);
        g2.drawRect((int) p[1].getX() - 5, (int) p[1].getY() - 5, 10, 10);
        g2.drawRect((int) p[2].getX() - 5, (int) p[2].getY() - 5, 10, 10);
        g2.setColor(Color.red);
        for (Point2D p1 : p) {
            g2.drawOval((int) p1.getX() - 1, (int) p1.getY() - 1, 2, 2);
        }

    }
}
