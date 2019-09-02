package exerc1;

import javax.swing.JOptionPane;

public class View {
	//Exibir mensagem simples
	public static void exibirMsg(String title, String msg) {
		JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);
	}
	//Exibir mensagem de erro
	public static void exibirMsgErro(String title, String msg) {
		JOptionPane.showMessageDialog(null, msg, title, JOptionPane.ERROR_MESSAGE);
	}
	//Solicitar dados Long
	public static long lerDadoLong(String title, String msg) {
		String tmpData = null;
		boolean flag = false;
		do {
			try {
				tmpData = JOptionPane.showInputDialog(null, msg, title, JOptionPane.QUESTION_MESSAGE);
				if(tmpData == null)
					throw new NullPointerException();
				Utils.validaLong(Long.parseLong(tmpData));
				flag = true;
			} catch (Exception e) {
				View.exibirMsgErro("ERROR", e.getMessage());
			}
		} while (!flag);
		return Long.parseLong(tmpData);
	}
	//Solicitar dado boolean
	public static boolean lerDadoBoolean(String title, String msg) {
		if(JOptionPane.showConfirmDialog(null, msg, title, JOptionPane.YES_NO_OPTION) == JOptionPane.NO_OPTION)
			return false;
		else
			return true;
	}
}
