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

package org.thole.hendrik.mandelbrot.gwt.client.impl;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.CssColor;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.RepeatingCommand;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Widget;

/**
 * 
 * @author Hendrik Thole, Manfred Thole
 * @date 20.07.2010
 *
 */
public class MandelBrotImpl {

	private  int size_x = 250;
	private  int size_y = 250;
	private final double xa  = -2.25;
	private final double xe  = 0.75;
	private final double ya  = -1.5;
	private final double ye  = 1.5;
	private final int itmax  = 20;
	private int itmax13;
	private int itmax23;



	public Widget getMandel() {

		final int size_screen = Math.min(Window.getClientWidth(), Window.getClientHeight())/2;
		size_y = size_screen;
		size_x = size_screen;

		final Canvas canvas = Canvas.createIfSupported();
		canvas.setCoordinateSpaceHeight(size_y);
		canvas.setCoordinateSpaceWidth(size_y);
		canvas.setHeight(size_y+"px");
		canvas.setWidth(size_x+"px");
		final Context2d context = canvas.getContext2d();


		itmax13 = itmax*1/3;
		itmax23 = itmax*2/3;

		paintMandel(context);

		return canvas;
	}


	/**
	 * Calculate the Set
	 * @param canvas
	 */
	private double ix, iy, zr, zi, zrq, ziq;
	private int it;
	private final void paintMandel(final Context2d context) {
		final double inx = (xe - xa) / size_x;
		final double iny = (ye - ya) / size_y;

		ix = xa;
		Scheduler.get().scheduleIncremental(new RepeatingCommand() {

			@Override
			public boolean execute() {
				if (ix <= xe) {
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

						context.setFillStyle(it2C(it));
						context.fillRect((ix-xa)/inx, (iy-ya)/iny, 1, 1);
					}
					ix += inx;
					return true;
				}
				return false;
			}
		});

		//		for (ix = xa; ix <= xe; ix += inx) {
		//			for (iy = ya; iy <= ye; iy += iny) {
		//				it = 0;
		//				zr = 0;
		//				zi = 0;
		//				do {
		//					zrq = zr * zr;
		//					ziq = zi * zi;
		//					it++;
		//					zi = 2 * zr * zi + iy;
		//					zr = zrq - ziq + ix;
		//				} while (zrq + ziq < 4 && it < itmax);
		//
		//				context.setFillStyle(it2C(it));
		//				context.fillRect((ix-xa)/inx, (iy-ya)/iny, 1, 1);
		//			}
		//		}
	}


	/**
	 * get the color.
	 * @param it
	 * @return Color
	 */
	private final CssColor it2C(final int it) {
		if (it < itmax13) {
			return CssColor.make(it*255/itmax13, 0, 0);
		}
		if (it < itmax23) {
			return CssColor.make(0, (it-itmax13)*255/itmax13, 0);
		}
		return CssColor.make(0, 0, (it-itmax23)*255/itmax13);
	}
}
