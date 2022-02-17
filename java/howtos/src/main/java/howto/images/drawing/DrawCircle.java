/*
 * To the extent possible under law, the ImageJ developers have waived
 * all copyright and related or neighboring rights to this tutorial code.
 *
 * See the Unlicense for details:
 *     https://unlicense.org/
 */

package howto.images.drawing;

import net.imagej.ImageJ;
import net.imglib2.RandomAccess;
import net.imglib2.algorithm.region.hypersphere.HyperSphere;
import net.imglib2.img.Img;
import net.imglib2.type.numeric.real.DoubleType;

/**
 * How to draw a circle
 *
 * @author Deborah Schmidt
 */
public class DrawCircle {

	private static void run() {

		ImageJ ij = new ImageJ();

		Img<DoubleType> img = ij.op().create().img(new long[] { 256, 254 });

		RandomAccess<DoubleType> ra = img.randomAccess();
		ra.setPosition(new long[] { 70, 98 });

		// draws circle of radius 30 at position [70, 98]
		new HyperSphere<>(img, ra, 30).forEach(value -> value.set(25));

		ij.ui().show(img);

	}

	public static void main(String...args) {
		run();
	}
}
