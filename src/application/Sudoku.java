package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Sudoku {

	private int[][] grid;
	private int size, sizever,sizegor;
	private String filePath;

	public Sudoku(int[][] grid, int size, int sizever, int sizegor) {
		grid = this.grid;
		size = this.size;//����� ������
		sizever = this.sizever;//������ ������������
		sizegor = this.sizegor;//������ ��������������
	}

	public Sudoku() {

	}

	public void count_line() {// ������� ���������� �� �����
		this.size = 0;
		try (BufferedReader br = new BufferedReader(new FileReader(this.filePath))) {// ������ ������ �� ������

		@SuppressWarnings("unused")//��������� ��������� ������������ ��������������
		String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				this.size++;

			}

		} catch (IOException e) {// ����������
			e.printStackTrace();// �������� ������ 
		}

	}

	public void initGridFile() {//������������� �� �����  �����
		this.grid = new int[this.size][this.size];

		try {

			File file = new File(this.filePath);
			Scanner sc = new Scanner(file);
			while (sc.hasNext()) {

				for (int i = 0; i < this.size; i++) {
					for (int j = 0; j < this.size; j++) {
						this.grid[i][j] = sc.nextInt();//����������� �������� �����

					}
				}

			}
			sc.close();

		} catch (IOException e) {// ����������
			e.printStackTrace();// �������� ������ 
		}

	}

       public void initGrid() {//������ �������

		this.grid = new int[this.size][this.size];// ������� 

		for (int i = 0; i < this.size; i++) {
			for (int j = 0; j < this.size; j++) {
				this.grid[i][j] = 0;

			}
		}
	}

	public void resetGrid() {// �������� �����
		if (grid != null) {
			for (int i = 0; i < this.size; i++) {
				for (int j = 0; j < this.size; j++) {
					this.grid[i][j] = 0;
				}

			}
		}
	}

	/*public void diplay_grid() {

		for (int i = 0; i < this.size; i++) {
			for (int j = 0; j < this.size; j++) {
				System.out.println(this.grid[i][j]);

			}
		}

	}*/

	public boolean ValidGrid() {//��������� �� �������� ����� ��� �����
		for (int i = 0; i < this.grid.length; i++) {
			for (int j = 0; j < this.grid.length; j++) {
				if (this.grid[i][j] > getSize() || this.grid[i][j] < 0) {
					return false;
				}
			}
		}
		return true;
	}
	
	public void setSizeCell() {//������� ��������� ��������� 
		if (this.size == 4) {
			this.sizever = 2;
			this.sizegor = 2;
		} else if (this.size == 6) {
			this.sizever = 2;
			this.sizegor = 3;
		} else if (this.size == 9) {
			this.sizever = 3;
			this.sizegor = 3;
		} 
		}


	public boolean notInColumn(int k, int j) {// �������� � �������

		int i;
		for (i = 0; i < this.size; i++) {
			if (this.grid[i][j] == k)
				return false;
		}
		return true;
	}

	public boolean notInRow(int k, int i) {//�������� � ����
		int j;
		for (j = 0; j < this.size; j++) {
			if (this.grid[i][j] == k)
				return false;
		}
		return true;
	}

	public boolean notInCell(int k, int i, int j) {//�������� � ������

		int i2 = i - (i % this.sizever);
		int j2 = j - (j % this.sizegor);
		for (i = i2; i < i2 + this.sizever; i++)
			for (j = j2; j < j2 + this.sizegor; j++)
				if (this.grid[i][j] == k)
					return false;
		return true;
	}

	public boolean isValid(int position) {// �������� ����������

		if (position == this.size * this.size)
			return true;

		int i = position / this.size;
		int j = position % this.size;

		if (this.grid[i][j] != 0) {
			return isValid(position + 1);
		}

		int k;
		for (k = 1; k <= this.size; k++) {

			if (notInColumn(k, j) == true && notInRow(k, i) == true && notInCell(k, i, j) == true) {

				this.grid[i][j] = k;

				if (isValid(position + 1))
					return true;
			}
		}
		this.grid[i][j] = 0;

		return false;
	}

	public int[][] getGrid() {//������� �����
		return grid;
	}

	public boolean isEmpty() {//���� �����
		int a = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {
				a = a + this.grid[i][j];
			}
		}
		if (a == 0) {
			return true;
		}
		return false;
	}

	public void setGrid(int[][] grid) {//��������� �������� �����
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {
				this.grid[i][j] = grid[i][j];
			}
		}
	}

	public int getSizever() {//�������� ������������ ������
		return sizever;
	}

	public void setSizever(int sizever) {//���������� ������������ ������
		this.sizever = sizever;
	}

	public int getSizegor() {//�������� �������������� ������
		return sizegor;
	}

	public void setSizegor(int sizegor) {//���������� �������������� ������
		this.sizegor = sizegor;
	}

	public String getFilePath() {//�������� ���� � �����
		return filePath;
	}

	public void setSize(int size) {//���� �������
		this.size = size;
	}

	public void setFilePath(String filePath) {//���� ���� � �����
		this.filePath = filePath;
	}

	public int getSize() {// �������� ������
		return size;
	}

}
