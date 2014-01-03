import javax.xml.bind.annotation.XmlAttribute;

/**
 * 
 */

/**
 * This class actually houses the pp data attributes
 * 
 * @author Doug Blase
 * 
 */
public class Powerplay {

	private int ppg;
	
	private int ppc;

	private int pks;
	
	private int pkc;

	/**
	 * @return the ppg
	 */
  @XmlAttribute(name = "ppg")
	public int getPpg() {
		return ppg;
	}

	/**
	 * @param ppg
	 *            the ppg to set
	 */
	public void setPpg(int ppg) {
		this.ppg = ppg;
	}

	/**
	 * @return the ppc
	 */
@XmlAttribute(name = "ppopp")
	public int getPpc() {
		return ppc;
	}

	/**
	 * @param ppc
	 *            the ppc to set
	 */
	public void setPpc(int ppc) {
		this.ppc = ppc;
	}

	/**
	 * @return the pks
	 */
	public int getPks() {
		return pks;
	}

	/**
	 * @param pks
	 *            the pks to set
	 */
  @XmlAttribute(name = "pk")
	public void setPks(int pks) {
		this.pks = pks;
	}

	/**
	 * @return the pkc
	 */
@XmlAttribute(name = "pkopp")
	public int getPkc() {
		return pkc;
	}

	/**
	 * @param pkc
	 *            the pkc to set
	 */
	public void setPkc(int pkc) {
		this.pkc = pkc;
	}

}
