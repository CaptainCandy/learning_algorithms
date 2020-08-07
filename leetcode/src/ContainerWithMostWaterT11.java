public class ContainerWithMostWaterT11 {
	public int maxArea(int[] height) {
		int max = 0;
		for (int l = 1; l <= height.length; l++) {
			for (int i = 0; i + l < height.length; i++) {
				int h = Math.min(height[i], height[i + l]);
				if (h * l > max) max = h * l;
			}
		}
		return max;
	}

	public int maxAreaDoublePoint(int[] height) {
		int i = 0, j = height.length - 1;
		int t = height.length - 1;
		int max = 0;
		while (i != j) {
			if (height[i] <= height[j]) {
				if (max < height[i] * t)
					max = height[i] * t;
				i += 1;
				t -= 1;
			}
			else {
				if (max < height[j] * t)
					max = height[j] * t;
				j -= 1;
				t -= 1;
			}
		}

		return max;
	}
}
