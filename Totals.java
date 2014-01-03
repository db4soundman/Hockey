import javax.xml.bind.annotation.XmlElement;

/**
 * 
 */

/**
 * Contains team data, specifically the PowerPlay info
 * 
 * @author Doug Blase
 * 
 */
public class Totals {

	private Powerplay ppStats;

	/**
	 * @return the ppStats
	 */
  @XmlElement(name = "powerplay", required = true)
	public Powerplay getPpStats() {
		return ppStats;
	}

	/**
	 * @param ppStats
	 *            the ppStats to set
	 */
	public void setPpStats(Powerplay ppStats) {
		this.ppStats = ppStats;
	}

}
