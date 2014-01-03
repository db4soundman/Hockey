import javax.xml.bind.annotation.XmlAttribute;

public class PenaltyStats {
	private int penalties;
	private int pim;

	/**
	 * @return the penalties
	 */
	@XmlAttribute(name = "count")
	public int getPenalties() {
		return penalties;
	}

	/**
	 * @param penalties
	 *            the penalties to set
	 */
	public void setPenalties(int penalties) {
		this.penalties = penalties;
	}

	/**
	 * @return the pim
	 */
	@XmlAttribute(name = "minutes")
	public int getPim() {
		return pim;
	}

	/**
	 * @param pim
	 *            the pim to set
	 */
	public void setPim(int pim) {
		this.pim = pim;
	}

}
