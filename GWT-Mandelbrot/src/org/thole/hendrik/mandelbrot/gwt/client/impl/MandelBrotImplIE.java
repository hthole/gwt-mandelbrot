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

import org.thole.hendrik.mandelbrot.gwt.client.res.MandelBundle;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

/**
 * 
 * This is an implementation for Internet Explorer. It just displays an image
 * of what others would see. There is no canvas element in IE (<= IE8) and as we
 * don't want to rely on excanvas.js we're just displaying an image.
 * 
 * @author Hendrik Thole
 * @date 20.07.2010
 *
 */
public class MandelBrotImplIE extends MandelBrotImpl {

	@Override
	public Widget getMandel() {

		final Image mandel = new Image(MandelBundle.INST.mandelImg());
		mandel.setTitle("As there is no canvas element in IE you are being displayed an image of the output - nothing is calculated here.");
		return mandel;
	}
}
