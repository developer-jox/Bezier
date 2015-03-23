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
import java.awt.geom.Rectangle2D;
import java.util.Random;
import javax.swing.JPanel;

/**
 *
 * @author developer-jox <developer.jox@gmail.com>
 */
public class Bezier extends JPanel {

    static final int NRCURVES = 3;
//    static final int NRPOINTS = 10;
    static final int NRPOINTS = 1 + 3 * NRCURVES;

    Point2D[] p = new Point2D.Double[NRPOINTS];
    double[] pntVx = new double[NRPOINTS];
    double[] pntVy = new double[NRPOINTS];

    CubicCurve2D q = new CubicCurve2D.Double();
    CubicCurve2D q2 = new CubicCurve2D.Double();
    CubicCurve2D q3 = new CubicCurve2D.Double();

    boolean init = true;

    public Bezier() {
        Dimension res = Toolkit.getDefaultToolkit().getScreenSize();
        setPreferredSize(new Dimension(res.width, res.height - 79));
        setSize(res.width, res.height - 79);
//        setPreferredSize(new Dimension(550, 550));
        setDoubleBuffered(true);

        Random rndX = new Random();
        Random rndY = new Random();

        p[0] = new Point2D.Double(rndX.nextInt(getWidth() - 100) + 1, rndY.nextInt(getHeight() - 100) + 1); //q1 start is q3 end
        rndX = new Random();
        rndY = new Random();
        p[1] = new Point2D.Double(rndX.nextInt(getWidth() - 100) + 1, rndY.nextInt(getHeight() - 100) + 1); //ctrl1
        rndX = new Random();
        rndY = new Random();
        p[2] = new Point2D.Double(rndX.nextInt(getWidth() - 100) + 1, rndY.nextInt(getHeight() - 100) + 1); //ctrl2
        rndX = new Random();
        rndY = new Random();
        p[3] = new Point2D.Double(rndX.nextInt(getWidth() - 100) + 1, rndY.nextInt(getHeight() - 100) + 1); //q1 end is q2 start
        rndX = new Random();
        rndY = new Random();
        p[4] = new Point2D.Double(rndX.nextInt(getWidth() - 100) + 1, rndY.nextInt(getHeight() - 100) + 1); //ctrl1
        rndX = new Random();
        rndY = new Random();
        p[5] = new Point2D.Double(rndX.nextInt(getWidth() - 100) + 1, rndY.nextInt(getHeight() - 100) + 1); //ctrl2
        rndX = new Random();
        rndY = new Random();
        p[6] = new Point2D.Double(rndX.nextInt(getWidth() - 100) + 1, rndY.nextInt(getHeight() - 100) + 1); //q2 end is q3 start 
        rndX = new Random();
        rndY = new Random();
        p[7] = new Point2D.Double(rndX.nextInt(getWidth() - 100) + 1, rndY.nextInt(getHeight() - 100) + 1); //ctrl1
        rndX = new Random();
        rndY = new Random();
        p[8] = new Point2D.Double(rndX.nextInt(getWidth() - 100) + 1, rndY.nextInt(getHeight() - 100) + 1); //ctrl2
        rndX = new Random();
        rndY = new Random();
        p[9] = new Point2D.Double(rndX.nextInt(getWidth() - 100) + 1, rndY.nextInt(getHeight() - 100) + 1); //q1 start is q3 end
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
        if (init) {
            for (int i = 0; i < p.length; i++) {
                pntVx[i] = 2;
                pntVy[i] = 2;
            }
            init = false;
        }
        while (true) {
            for (int i = 0; i < pntVx.length; ++i) {
                if (p[i].getX() > getWidth() - 100 || p[i].getX() < 100) {
                    if (Math.signum(pntVx[i]) >= 0.0) {
                        if (Math.random() >= 0.5 && pntVx[i] < 10) {
                            Random rnd = new Random();
                            pntVx[i] += rnd.nextDouble() + 0.01;
                        }
                        if (Math.random() < 0.5 && pntVx[i] > -10 && pntVx[i] > 2) {
                            Random rnd = new Random();
                            pntVx[i] -= rnd.nextDouble() + 0.01;
                        }
                    }
                    if (Math.signum(pntVx[i]) == -1.0) {
                        if (Math.random() >= 0.5 && pntVx[i] < 10) {
                            Random rnd = new Random();
                            pntVx[i] += rnd.nextDouble() + 0.01;
                        }
                        if (Math.random() < 0.5 && pntVx[i] > -10 && pntVx[i] > 2) {
                            Random rnd = new Random();
                            pntVx[i] -= rnd.nextDouble() + 0.01;
                        }
                    }
                }
            }
            for (int i = 0; i < pntVy.length; ++i) {
                if (p[i].getY() > getHeight() - 100 || p[i].getY() < 100) {
                    if (Math.signum(pntVy[i]) >= 0.0) {
                        if (Math.random() >= 0.5 && pntVy[i] < 10) {
                            Random rnd = new Random();
                            pntVy[i] += rnd.nextDouble() + 0.01;
                        }
                        if (Math.random() < 0.5 && pntVy[i] > -10) {
                            Random rnd = new Random();
                            pntVy[i] -= rnd.nextDouble() + 0.01;
                        }
                    }
                    if (Math.signum(pntVy[i]) == -1.0) {
                        if (Math.random() >= 0.5 && pntVy[i] < 10) {
                            Random rnd = new Random();
                            pntVy[i] += rnd.nextDouble() + 0.01;
                        }
                        if (Math.random() < 0.5 && pntVy[i] > -10) {
                            Random rnd = new Random();
                            pntVy[i] -= rnd.nextDouble() + 0.01;
                        }
                    }
                }
            }
            for (int i = 0; i < p.length - 1; i++) {
                if (p[i].getX() > getWidth() - 100) {
//                    System.out.println("X > 400");
                    p[i].setLocation(getWidth() - 100, p[i].getY());
                    pntVx[i] = -pntVx[i];
                }
                if (p[i].getX() < 100) {
                    p[i].setLocation(100, p[i].getY());
//                    System.out.println("X < 100");
                    pntVx[i] = -pntVx[i];
                }
                if (p[i].getY() > getHeight() - 100) {
//                    System.out.println("Y > 100");
                    p[i].setLocation(p[i].getX(), getHeight() - 100);
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
            p[9].setLocation(p[0].getX(), p[0].getY());
//            p[4].setLocation(p[1].getX() * (-1), p[1].getY() * (-1));
//            p[5].setLocation(p[2].getX(), p[2].getY());
//            p[7].setLocation(p[5].getX() * (-1), p[5].getY() * (-1));
//            p[8].setLocation(p[1].getX() * (-1), p[1].getY() * (-1));

            q.setCurve(p, 0);
            q2.setCurve(p, 3);
            q3.setCurve(p, 6);

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
        g2.drawRect(100, 100, getWidth() - 200, getHeight() - 200);
        g2.setStroke(new BasicStroke(4f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Rectangle2D test = q.getBounds2D();
        Rectangle2D test2 = q2.getBounds2D();
        Rectangle2D test3 = q3.getBounds2D();
        g2.setColor(Color.black);
        g2.draw(q);
//        g2.draw(test);
        g2.setColor(Color.gray);
        g2.draw(q2);
//        g2.draw(test2);
        g2.setColor(Color.lightGray);
        g2.draw(q3);
//        g2.draw(test3);
//        g2.fillRect((int) p[3].getX() - 5, (int) p[3].getY() - 5, 10, 10);
//        g2.fillRect((int) p[0].getX() - 5, (int) p[0].getY() - 5, 10, 10);
//        g2.drawRect((int) p[1].getX() - 5, (int) p[1].getY() - 5, 10, 10);
//        g2.drawRect((int) p[2].getX() - 5, (int) p[2].getY() - 5, 10, 10);
//        g2.setColor(Color.red);
//        for (Point2D p1 : p) {
//            g2.drawOval((int) p1.getX() - 2, (int) p1.getY() - 2, 4, 4);
//        }
//        g2.drawString("Vx", 100, getHeight() - 80);

    }
}
