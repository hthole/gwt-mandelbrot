/*
 * Copyright (c) 1999/2000/2010 Manfred Thole, Hendrik Thole
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the
 * Free Software Foundation; either version 2 of the License, or (at your
 * option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 675 Mass Ave, Cambridge, MA 02139, USA.
 *
 *
 */

package org.thole.hendrik.mandelbrot.gwt.client;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.widgetideas.graphics.client.Color;
import com.google.gwt.widgetideas.graphics.client.GWTCanvas;



/**
 * Mandelbrot Set.
 * 
 * @author Manfred Thole, Hendrik Thole
 * @date 19.07.2010
 *
 */
public class MandelBrot extends FlowPanel {

	private final int size_x = 250;
	private final int size_y = 250;
	private final double xa  = -2.25;
	private final double xe  = 0.75;
	private final double ya  = -1.5;
	private final double ye  = 1.5;
	private final int itmax  = 20;
	private int itmax13;
	private int itmax23;


	public MandelBrot() {
		final GWTCanvas canvas = new GWTCanvas(size_x, size_y);

		canvas.setBackgroundColor(Color.BLACK);
		itmax13 = itmax*1/3;
		itmax23 = itmax*2/3;

		paintMandel(canvas);
		this.add(canvas);
	}

	private void paintMandel(final GWTCanvas canvas) {

		double ix, iy, zr, zi, zrq, ziq;
		int it;
		final double inx = (xe - xa) / size_x;
		final double iny = (ye - ya) / size_y;

		for (ix = xa; ix <= xe; ix += inx) {
			for (iy = ya; iy <= ye; iy += iny) {
				it = 0;
				zr = 0;
				zi = 0;
				do {
					zrq = zr * zr;
					ziq = zi * zi;
					it++;
					zi = 2 * zr * zi + iy;
					zr = zrq - ziq + ix;
				} while (zrq + ziq < 4 && it < itmax);

				canvas.setFillStyle(it2C(it));
				canvas.fillRect((ix-xa)/inx, (iy-ya)/iny, 1, 1);
			}
		}
	}


	private final Color it2C(final int it) {
		if (it < itmax13) {
			return new Color(it*255/itmax13, 0, 0);
		}
		if (it < itmax23) {
			return new Color(0, (it-itmax13)*255/itmax13, 0);
		}
		return new Color(0, 0, (it-itmax23)*255/itmax13);
	}
}