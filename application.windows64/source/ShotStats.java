import javax.xml.bind.annotation.XmlAttribute;

/**
 * 
 */

/**
 * @author Doug Blase
 * 
 */
public class ShotStats {
	private int goals;
	private int assists;
	private int pts;

	/**
	 * @return the goals
	 */
	@XmlAttribute(name = "g")
	public int getGoals() {
		return goals;
	}

	/**
	 * @param goals
	 *            the goals to set
	 */
	public void setGoals(int goals) {
		this.goals = goals;
	}

	/**
	 * @return the assists
	 */
	@XmlAttribute(name = "a")
	public int getAssists() {
		return assists;
	}

	/**
	 * @param assists
	 *            the assists to set
	 */
	public void setAssists(int assists) {
		this.assists = assists;
	}

	/**
	 * @return the pts
	 */
	@XmlAttribute(name = "pts")
	public int getPts() {
		return pts;
	}

	/**
	 * @param pts
	 *            the pts to set
	 */
	public void setPts(int pts) {
		this.pts = pts;
	}

}
