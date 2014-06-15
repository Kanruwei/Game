package Cube;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class CubeState {

	public static String[] CubeList = new String[] { "L", "J", "O", "S", "Z",
			"I", "T" };

	public static Cube createCube() {

		Cube currentCube = null;

		Random random = new Random();
		int indexofNum = random.nextInt(CubeList.length);
		String index = CubeList[indexofNum];

		if (index.equals("L")) {
			return currentCube = new L();
		} else if (index.equals("J")) {
			return currentCube = new J();
		} else if (index.equals("S")) {
			return currentCube = new S();
		} else if (index.equals("Z")) {
			return currentCube = new Z();
		} else if (index.equals("O")) {
			return currentCube = new O();
		} else if (index.equals("T")) {
			return currentCube = new T();
		} else {
			return currentCube = new I();
		}
	}

}
