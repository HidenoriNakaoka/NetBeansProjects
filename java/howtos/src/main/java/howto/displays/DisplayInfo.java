/*
 * To the extent possible under law, the ImageJ developers have waived
 * all copyright and related or neighboring rights to this tutorial code.
 *
 * See the Unlicense for details:
 *     https://unlicense.org/
 */

package howto.displays;

import net.imagej.ImageJ;
import org.scijava.ui.DialogPrompt;

/**
 * How to display general information in a popup
 *
 * @author Deborah Schmidt
 */
public class DisplayInfo {

	private static void run() {
		ImageJ ij = new ImageJ();
		ij.ui().showDialog("Everything is fine", DialogPrompt.MessageType.INFORMATION_MESSAGE);
	}

	public static void main(String...args) {
		run();
	}

}
