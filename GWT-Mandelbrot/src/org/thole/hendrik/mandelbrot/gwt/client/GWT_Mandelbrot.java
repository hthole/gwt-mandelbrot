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

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootLayoutPanel;

/**
 * 
 * @author Hendrik Thole
 * @date 19.07.2010
 *
 */
public class GWT_Mandelbrot implements EntryPoint {

	@Override
	public void onModuleLoad() {
		final MandelBrot mandel = new MandelBrot();
		RootLayoutPanel.get().add(mandel);
	}
}
