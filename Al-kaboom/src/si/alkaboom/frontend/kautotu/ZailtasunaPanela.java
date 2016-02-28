package si.alkaboom.frontend.kautotu;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.text.AbstractDocument;

import si.alkaboom.backend.AlKaboom;
import si.alkaboom.externals.NumericAndLengthFilter;
import si.alkaboom.externals.SpringUtilities;
import si.alkaboom.salbuespenak.AlKaboomSalbuespena;

public class ZailtasunaPanela extends JPanel {
	private static final long serialVersionUID = -1087859968284089074L;
	private JLabel laukiErrenkada, laukiZutabe, minaKop;
	private JTextField laukiErrenkadaField, laukiZutabeField, minaKopField;
	private JButton ok, cancel;
	private int[] balioak;

	public ZailtasunaPanela() {
		this.setLayout(new SpringLayout());
		laukiErrenkada = new JLabel("Lauki errenkada kopurua:", SwingConstants.TRAILING);
		laukiErrenkadaField = new JTextField();
		((AbstractDocument) laukiErrenkadaField.getDocument()).setDocumentFilter(new NumericAndLengthFilter(3));
		laukiErrenkada.setLabelFor(laukiErrenkadaField);
		laukiZutabe = new JLabel("Lauki zutabe kopurua:", SwingConstants.TRAILING);
		laukiZutabeField = new JTextField();
		((AbstractDocument) laukiZutabeField.getDocument()).setDocumentFilter(new NumericAndLengthFilter(3));
		laukiZutabe.setLabelFor(laukiZutabeField);
		minaKop = new JLabel("Mina kopurua:", SwingConstants.TRAILING);
		minaKopField = new JTextField();
		((AbstractDocument) minaKopField.getDocument()).setDocumentFilter(new NumericAndLengthFilter(3));
		minaKop.setLabelFor(minaKopField);
		ok = new JButton("OK");
		ok.addActionListener(gureAE -> this.balioakGorde());
		cancel = new JButton("Cancel");
		cancel.addActionListener(gureAE -> AlKaboom.getAlKaboom().getKautotu().kautotuIpini(false));
		this.add(laukiErrenkada);
		this.add(laukiErrenkadaField);
		this.add(laukiZutabe);
		this.add(laukiZutabeField);
		this.add(minaKop);
		this.add(minaKopField);
		this.add(ok);
		this.add(cancel);
		SpringUtilities.makeCompactGrid(this, 4, 2, 3, 3, 3, 3);
	}

	private void balioakGorde() {
		balioak = new int[3];
		try {
			balioak[0] = Integer.parseInt(laukiErrenkadaField.getText());
			balioak[1] = Integer.parseInt(laukiZutabeField.getText());
			balioak[2] = Integer.parseInt(minaKopField.getText());
		} catch (NumberFormatException e) {
			throw new AlKaboomSalbuespena("Sartutako balioak ez dira baliozkoak!", e);
		}
		AlKaboom.getAlKaboom().setBalioakCustom(balioak);
		AlKaboom.getAlKaboom().getKautotu().kautotuIpini(true);
	}

}
