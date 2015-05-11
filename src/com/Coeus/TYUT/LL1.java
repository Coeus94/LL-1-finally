package com.Coeus.TYUT;

import java.awt.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

public class LL1 {
	static String Vn[] = { "A", "B", "C" }; // ���ս����
	static String Vt[] = { "a", "b", "c", "d", "e", "#" }; // �ս����
	static String P[][] = new String[3][6]; // Ԥ�������
	static ArrayList<String> fenxi = new ArrayList<String>();
	static String inputString = ""; // ������ַ���
	static String action = "";// ����
	static String top = null;// ����ջջ���ַ�
	static String topinput = null;// �������봮���ַ�

	public static void init() {
		fenxi.add("#");
		fenxi.add("A");
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 6; j++) {
				P[i][j] = "error";
			}
		}
		P[0][0] = "cB/C";
		P[1][1] = "B/C";
		P[1][3] = "��/C";
		P[1][4] = "B/C";
		P[2][2] = "��/C";
		System.out.println("�ѹ����õ�Ԥ�������:");
		System.out
				.println("----------------------------------------------------------------------");
		for (int i = 0; i < 6; i++) {
			System.out.print("          " + Vt[i]);
		}
		System.out.println();
		System.out
				.println("----------------------------------------------------------------------");
		for (int i = 0; i < 3; i++) {
			System.out.print("   " + Vn[i] + "    ");
			for (int j = 0; j < 6; j++) {
				int l = 0;
				if (j > 0) {
					l = 10 - P[i][j - 1].length();
				}
				for (int k = 0; k < l; k++) {
					System.out.print(" ");
				}
				System.out.print(P[i][j] + " ");
			}
			System.out.println();
		}
		System.out
				.println("----------------------------------------------------------------------");
	}

	public static void input() {
		System.out.println("������Ҫ�����ķ��Ŵ�����#������");
		BufferedReader bfd = new BufferedReader(
				new InputStreamReader(System.in));
		try {
			inputString = bfd.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// �㷨������
	public static void judge() {
		if (fenxi.size() >= 1) {
			Iterator it = fenxi.iterator();
			while (it.hasNext()) {
				top = (String) it.next();
			}
			while (top.length() == 0) {
				fenxi.remove(fenxi.size() - 1);
				Iterator its = fenxi.iterator();
				while (its.hasNext()) {
					top = (String) its.next();
				}
			}
		}
		if (inputString.length() >= 1) {
			topinput = inputString.substring(0, 1);
		}
		// System.out.println("top=" + top + "  topinput:" + topinput);
		M(top, topinput);
		prt(fenxi, inputString, action);
		if (!(action == "succ")) {
			if (!(action == "error")) {
				String actions[] = action.split("/");
				if (actions[1].equals("C"))// ��������һ���ַ�
				{
					if (actions[0].equals("��")) {
						if (fenxi.size() > 1) {
							fenxi.remove(fenxi.size() - 1);
						}
						if (inputString.length() > 1) {
							inputString = inputString.substring(1,
									inputString.length());
						}// �������봮��һ
						judge();
					} else {
						fenxi.remove(fenxi.size() - 1);
						String tmp[] = actions[0].split("");
						for (int i = 0; i < tmp.length; i++) {
							fenxi.add(tmp[i]);
						}
						if (inputString.length() > 1) {
							inputString = inputString.substring(1,
									inputString.length());
						}// �������봮��һ
						judge();
					}
				} else if (actions[1].equals("R")) {
					if (actions[0].equals("��")) {
						fenxi.remove(fenxi.size() - 1);
						if (inputString.length() > 1) {
							inputString = inputString.substring(1,
									inputString.length());
						}// �������봮��һ
						judge();
					} else {
						fenxi.remove(fenxi.size() - 1);
						String tmp[] = actions[0].split("");
						for (int i = 0; i < tmp.length; i++) {
							fenxi.add(tmp[i]);
						}
						judge();
					}
				} else {
					System.out.print("#1����");
				}
			} else {
				System.out.println("error ���ַ��������ϸ��ķ���");
			}
		} else {
			System.out.println("�������������ַ������ϸ��ķ���");
		}
	}

	public static void prt(ArrayList<String> fen, String in, String ac) {
		int n = 0;
		Iterator iterator = fenxi.iterator();
		while (iterator.hasNext()) {
			System.out.print(iterator.next());
			n++;
		}
		System.out.println("\t\t" + in + "\t\t" + ac);
	}

	public static void M(String a, String b) {
		if (a.equals("#") && b.equals("#")) {
			action = "succ";
		} else {
			int indexvn = -1;
			int indexvt = -1;
			for (int i = 0; i < Vn.length; i++) {
				if (Vn[i].equals(a)) {
					indexvn = i;
					break;
				}
			}
			for (int i = 0; i < Vt.length; i++) {
				if (Vt[i].equals(b)) {
					indexvt = i;
					break;
				}
			}
			if (indexvn >= 0 && indexvt >= 0) {
				action = P[indexvn][indexvt];
			} else {
				boolean aa = false;// �ж�a�Ƿ�����vt��a�����κι����Ҳ����ײ�
				for (int i = 0; i < Vt.length; i++) {
					if (top.equals(Vt[i])) {
						aa = true;
					}
					for (int m = 0; m < 3; m++) {
						for (int n = 0; n < 6; n++) {
							String tString = P[m][n];
							String tmp[] = tString.split("/");
							if (!tmp[0].startsWith(top)) {
								aa = true;
							}
						}
					}
				}
				if (aa) {
					action = "��/C";
				}
			}
		}
	}

	public static void main(String[] args) {
		init();
		input();
		System.out.println("����ջ\t\t" + "�������봮\t\t" + "����\t\t");
		judge();
	}

}
