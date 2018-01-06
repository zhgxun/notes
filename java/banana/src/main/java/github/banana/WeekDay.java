package github.banana;

public enum WeekDay {
	SUN("星期日"), MON("星期一"), TUE("星期二"), WED("星期三"), THU("星期四"), FRI("星期五"), SAT("星期六");
	
	// 可定义属性
	private String chinese;
	
	// 对属性进行赋值
	private WeekDay(String chinese) {
		this.chinese = chinese;
	}
	
	// 获取属性的值
	public String toChinese() {
		return this.chinese;
	}
}
