package ru.samsung.itschool.book.cells;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridLayout;

public class CellsActivity extends Activity implements OnClickListener, OnLongClickListener {

    private int WIDTH = 3;
    private int HEIGHT = 6;

    private Button[][] cells;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.cells);
        makeCells();

        generate();

    }
    public int winRed = 0;
    public int winBlue = 0;
    public int diagonal()
    {
        int Red = getResources().getColor(R.color.bestRed);
        int Blue = getResources().getColor(R.color.bestBlue);
        int count = 0;
        for (int i = 0; i < 3; i++)
        {
            int color = ((ColorDrawable) cells[i][i].getBackground()).getColor();
            if (color == Blue)
            {
                count++;
            }
            else if (color == Red)
            {
                count--;
            }
        }
        if(count == -3)
        {
            return 1;
        }
        if(count == 3)
        {
            return -1;
        }
        count = 0;
        for (int i = 0; i < 3; i++)
        {
            int color = ((ColorDrawable) cells[i][2 - i].getBackground()).getColor();
            if (color == Blue)
            {
                count++;
            }
            else if (color == Red)
            {
                count--;
            }
        }
        if(count == -3)
        {
            return 1;
        }
        if(count == 3)
        {
            return -1;
        }
        return 0;
    }
    public int horizontal()
    {
        int Red = getResources().getColor(R.color.bestRed);
        int Blue = getResources().getColor(R.color.bestBlue);
        for (int i = 0; i < 3; i++)
        {
            int count = 0;
            for (int j = 0; j < 3; j++)
            {
                int color = ((ColorDrawable)cells[i][j].getBackground()).getColor();
                if(color == Blue)
                {
                    count++;
                }
                else if(color == Red)
                {
                    count--;
                }
            }
            if(count == -3)
            {
                return 1;
            }
            if(count == 3)
            {
                return -1;
            }
        }
        return 0;
    }
    public int vertical()
    {
        int Red = getResources().getColor(R.color.bestRed);
        int Blue = getResources().getColor(R.color.bestBlue);
        for (int i = 0; i < 3; i++)
        {
            int count = 0;
            for (int j = 0; j < 3; j++)
            {
                int color = ((ColorDrawable)cells[j][i].getBackground()).getColor();
                if(color == Blue)
                {
                    count++;
                }
                else if(color == Red)
                {
                    count--;
                }
            }
            if(count == -3)
            {
                return 1;
            }
            if(count == 3)
            {
                return -1;
            }
        }
        return 0;
    }
    public int counter()
    {
        int count = 0;

        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                int color = ((ColorDrawable)cells[i][j].getBackground()).getColor();
                if(color != Color.WHITE)
                {
                    count++;
                }
            }
        }
        return count;
    }

    void generated()
    {
        for (int i = 0; i < HEIGHT; i++)
        {
            for (int j = 0; j < WIDTH; j++)
            {
                if(i < 3 && j < 3)
                {
                    cells[i][j].setBackgroundColor(Color.WHITE);
                }

            }
        }
    }

    void generate() {

        //Эту строку нужно удалить
        Task.showMessage(this, "Left - cross");
        Task.showMessage(this, "Right - nulls");
        Task.showMessage(this, "Нижние две клетки - счёт");
        Task.showMessage(this, "BLUE - nulls");
        Task.showMessage(this, "RED - cross");
        for (int i = 0; i < HEIGHT; i++)
        {
            for (int j = 0; j < WIDTH; j++)
            {
                if(i >= 3 || j >= 3)
                {
                    int transparent = getResources().getColor(R.color.transparent);
                    cells[i][j].setBackgroundColor(transparent);
                }

            }
        }
        cells[5][1].setBackgroundColor(Color.WHITE);
        cells[5][2].setBackgroundColor(Color.WHITE);
        cells[5][2].setText("0");
        cells[5][1].setText("0");
    }

    @Override
    public boolean onLongClick(View v) {
        Button tappedCell = (Button) v;
        int myColor = getResources().getColor(R.color.bestRed);
        //Получаем координтаты нажатой клетки
        int tappedX = getX(tappedCell);
        int tappedY = getY(tappedCell);
        cells[tappedX][tappedY].setBackgroundColor(myColor);
        cells[tappedX][tappedY].setText("");
        return true;
    }
    int num = 0;
    int part = 0;
    @Override
    public void onClick(View v) {
        //Эту строку нужно удалить
       // Stub.show(this, "Добавьте код в функцию активности onClick() - реакцию на нажатие на клетку");

        Button tappedCell = (Button) v;
        //Получаем координтаты нажатой клетки
        int tappedX = getX(tappedCell);
        int tappedY = getY(tappedCell);
        int colorer = ((ColorDrawable)cells[tappedX][tappedY].getBackground()).getColor();
        int Red = getResources().getColor(R.color.bestRed);
        int Blue = getResources().getColor(R.color.bestBlue);
        if(colorer == Color.WHITE)
        {
            if (num % 2 == 0) {
                cells[tappedX][tappedY].setBackgroundColor(Red);

            } else {
                cells[tappedX][tappedY].setBackgroundColor(Blue);

            }
            int count;
            count = horizontal();
            if(count == 1)
            {
                part++;
                part %= 2;
                num++;
                winRed++;
                Task.showMessage(this, "RED WIN!!!");
                cells[5][2].setText(winBlue + "");
                cells[5][1].setText(winRed + "");
                generated();
            }
            else if(count == -1)
            {
                part++;
                part %= 2;
                num++;
                winBlue++;
                Task.showMessage(this, "BLUE WIN!!!");
                cells[5][2].setText(winBlue + "");
                cells[5][1].setText(winRed + "");
                generated();
            }
            num++;
            count = vertical();
            if(count == 1)
            {
                part++;
                part %= 2;
                num = part;
                winRed++;
                Task.showMessage(this, "RED WIN!!!");
                cells[5][2].setText(winBlue + "");
                cells[5][1].setText(winRed + "");
                generated();
            }
            else if(count == -1)
            {
                part++;
                part %= 2;
                num = part;
                winBlue++;
                Task.showMessage(this, "BLUE WIN!!!");
                cells[5][2].setText(winBlue + "");
                cells[5][1].setText(winRed + "");
                generated();
            }
            count = diagonal();
            if(count == 1)
            {
                part++;
                part %= 2;
                num = part;
                winRed++;
                Task.showMessage(this, "RED WIN!!!");
                cells[5][2].setText(winBlue + "");
                cells[5][1].setText(winRed + "");
                generated();
            }
            else if(count == -1)
            {
                part++;
                part %= 2;
                num = part;
                winBlue++;
                Task.showMessage(this, "BLUE WIN!!!");
                cells[5][2].setText(winBlue + "");
                cells[5][1].setText(winRed + "");
                generated();
            }
            count = counter();
            if(count == 9)
            {
                part++;
                part %= 2;
                num = part;
                Task.showMessage(this, "DRAW");
                generated();
            }

        }


    }

	/*
     * NOT FOR THE BEGINNERS
	 * ==================================================
	 */

    int getY(View v) {
        return Integer.parseInt(((String) v.getTag()).split(",")[1]);
    }

    int getX(View v) {
        return Integer.parseInt(((String) v.getTag()).split(",")[0]);
    }

    void makeCells() {
        cells = new Button[HEIGHT][WIDTH];
        GridLayout cellsLayout = (GridLayout) findViewById(R.id.CellsLayout);
        cellsLayout.removeAllViews();
        cellsLayout.setColumnCount(WIDTH);
        for (int i = 0; i < HEIGHT; i++)
            for (int j = 0; j < WIDTH; j++) {
                LayoutInflater inflater = (LayoutInflater) getApplicationContext()
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                cells[i][j] = (Button) inflater.inflate(R.layout.cell, cellsLayout, false);
                cells[i][j].setOnClickListener(this);
                cells[i][j].setOnLongClickListener(this);
                cells[i][j].setTag(i + "," + j);
                cellsLayout.addView(cells[i][j]);
            }
    }



}