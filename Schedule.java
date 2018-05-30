
public class Schedule {

	// private int dateTime = 0;

	int day_of_week = 0;
	int start_time = 0;
	int end_time = 0;

	private String name = null;
	private int color = 0;

	public Schedule() {

	}

	public Schedule(int day, int start, int end, int color, String name) {

		day_of_week = day;
		start_time = start;
		end_time = end;

		this.color = color;
		this.name = name;

	}

	public void setDatatime(int day_of_week, int start_time, int end_time) {

		this.day_of_week = day_of_week;
		this.start_time = start_time;
		this.end_time = end_time;

		return;
	}

	public void writeName(String name) {
		this.name = name;
		return;

	}

	public void invite() {
		return;
	}

	public int getColor() {
		return this.color;
	}

	public String getName() {
		return this.name;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public int[] getDatetime() {
		int[] datetime = new int[3];
		datetime[0] = this.day_of_week;
		datetime[1] = this.start_time;
		datetime[2] = this.end_time;
		return datetime;
	}

	public String getString() {

		return this.name;
	}

	public boolean equals(Object o) {
		if (((Schedule) o).day_of_week == (day_of_week))
			if (((Schedule) o).start_time == (start_time))
				if (((Schedule) o).end_time == (end_time))
					if (((Schedule) o).color == (color))
						return ((Schedule) o).name.equals(name);
		return false;
	}

}
