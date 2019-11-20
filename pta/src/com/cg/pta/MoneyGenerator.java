package com.cg.pta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.cg.pta.object.Money;
import com.jogamp.opengl.util.texture.Texture;

public class MoneyGenerator {
	public static List<Money> create(boolean[][] grid, Texture[] textures) {
		Random random = new Random();
		List<Money> money = new ArrayList<Money>();
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (!grid[i][j] && random.nextFloat() < 0.1) {
					money.add(new Money(i, j, textures));
				}
			}
		}
		return money;
	}
	public static Money newMoney(boolean[][] grid,List<Money> money, Texture[] textures){
		Random r = new Random(System.currentTimeMillis());
		while( true ){
			int i = r.nextInt(grid.length);
			int j = r.nextInt(grid[0].length);
			if (!grid[i][j] && !hasMoneyAt(i,j,money) ) {
				return new Money(i,j,textures);
			}
		}
		//return null;
	}
	private static boolean hasMoneyAt(int row,int col,List<Money> money){
		for( Money t : money ){
			if( t.getRow() == row && t.getCol() == col )
				return true;
		}
		return false;
	}
}
