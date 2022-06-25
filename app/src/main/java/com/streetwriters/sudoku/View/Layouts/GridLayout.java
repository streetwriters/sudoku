package com.streetwriters.sudoku.View.Layouts;

import android.annotation.SuppressLint;
import android.content.Context;

import com.streetwriters.sudoku.Controller.OnClick.GridOnClick;
import com.streetwriters.sudoku.Functions.Utils.Singletons.GameState;
import com.streetwriters.sudoku.Functions.Utils.Singletons.UseGameState;
import com.streetwriters.sudoku.R;
import com.streetwriters.sudoku.View.Ui.HorizontalBorder;
import com.streetwriters.sudoku.View.Ui.VerticalBorder;
import com.streetwriters.sudoku.View.Ui.Row;
import com.streetwriters.sudoku.View.Ui.Grid;

public class GridLayout extends UseGameState {
    Context context;
    Grid grid;
    GameState gameState = GameState.getInstance();

    @SuppressLint("ClickableViewAccessibility")
    public GridLayout(Context context) {
        this.context = context;
        setGrid();
        grid.setOnTouchListener(new GridOnClick());
    }

    private void setGrid() {
        if (new Grid(context).findViewById(R.id.grid) == null) {
            grid = new Grid(context);
            grid.setId(R.id.grid);
        } else {
            grid = new Grid(context).findViewById(R.id.grid);
        }
    }

    public Grid getGrid() {
        return grid;
    }

//    public Grid arrange() {
//        int[] boxXPosition = new int[]{0, 3, 6};
//        for (int i = 0; i < 3; i++) {
//
//            grid.addView(arrangeBand(boxXPosition[i]));
//        }
//        return grid;
//    }
//
//    public HorizontalBorder arrangeBand(int boxXPosition) {
//        HorizontalBorder horizontalBorder = new HorizontalBorder(context);
//        int[] boxYPosition = new int[]{0, 3, 6};
//        for (int i = 0; i < 3; i++) {
//            horizontalBorder.addView(arrangeBox(boxXPosition, boxYPosition[i]));
//        }
//        return horizontalBorder;
//    }
//
//    public VerticalBorder arrangeBox(int boxXPosition, int boxYPosition) {
//        VerticalBorder verticalBorder = new VerticalBorder(context);
//        verticalBorder.setBoxProperties(boxXPosition, boxYPosition);
//        for (int i = 0; i < 3; i++) {
//            int X = i + boxXPosition;
//            verticalBorder.addView(arrangeBoxRow(X, boxYPosition));
//        }
//        return verticalBorder;
//    }
//
//    public BoxRow arrangeBoxRow(int X, int y) {
//        BoxRow boxRow = new BoxRow(context);
//
//        for (int i = 0; i < 3; i++) {
//            int Y = i + y;
//            int id = Integer.parseInt(X + "" + Y);
//            CellLayout cellLayout = new CellLayout(context, id);
//            cellLayout.addCell();
//
//            if (gameState.getUnSolvedPuzzle()[X][Y] != 0) {
//                cellLayout.setCellText(String.valueOf(gameState.getUnSolvedPuzzle()[X][Y]));
//                cellLayout.setFixedCellBackground();
//            } else if (gameState.getUserSolvedPuzzle()[X][Y] != 0)
//                cellLayout.setCellText(String.valueOf(gameState.getUserSolvedPuzzle()[X][Y]));
//
//            boxRow.addView(cellLayout.getCellView());
//        }
//        return boxRow;
//    }

    public Grid newArrangement(){
        for (int i = 0; i < 9; i++) {
            Row row = new Row(context);
            for (int j = 0; j < 9; j++) {
                 CellLayout cellLayout = new CellLayout(context,Integer.parseInt(i+""+j));

                if (isClue(i,j)) {
                    cellLayout.setCellText(String.valueOf(gameState.getUnSolvedPuzzle()[i][j]));
                    cellLayout.setFixedCellBackground();
                } else if (isCellFilled(i,j))
                    cellLayout.setCellText(String.valueOf(gameState.getUserSolvedPuzzle()[i][j]));

                 if(j>0&&j%3==0){
                     row.addView(new VerticalBorder(context));
                 }

                 row.addView(cellLayout.getCellView());
            }

            if(i>0&&i%3==0){
                  grid.addView(new HorizontalBorder(context));
            }

            grid.addView(row);
        }

        return grid;
    }
}
