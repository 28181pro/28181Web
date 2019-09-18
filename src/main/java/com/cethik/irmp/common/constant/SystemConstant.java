package com.cethik.irmp.common.constant;

/**
 * 系统级静态变量
 *
 * @author daniel.yu
 * @date 2019年6月23日 上午11:07:36
 */
public class SystemConstant {
	
	/**
	 * 超级管理员ID
	 */
	public static final long SUPER_ADMIN = 1;
	
	/**
	 * 数据标识
	 */
	public static final String DATA_ROWS = "rows";
	
	/**
	 * 未授权错误代码
	 */
	public static final int UNAUTHORIZATION_CODE = 401;


	/**
	 *
	 * 市公司：：：
	 *
	 */
	public enum LocationType {
		/**
		 * 市公司
		 */
		CITY(0),
		/**
		 * 县公司
		 */
		COUNTY(1),
		/**
		 * 供电所
		 */
		TOWN(2),
		/**
		 * 线路
		 */
		LINE(3);

		private int value;

		private LocationType(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}

	/**
	 * 菜单类型
	 *
	 * @author daniel.yu
	 * @date 2019年6月23日 上午11:07:36
	 */
    public enum MenuType {
        /**
         * 目录
         */
    	CATALOG(0),
        /**
         * 菜单
         */
        MENU(1),
        /**
         * 按钮
         */
        BUTTON(2);

        private int value;

        private MenuType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
    
    /**
     * 定时任务状态
     *
     * @author daniel.yu
	 * @date 2019年6月23日 上午11:07:36
     */
    public enum ScheduleStatus {
        /**
         * 正常
         */
    	NORMAL(1),
        /**
         * 暂停
         */
    	PAUSE(0);

        private int value;

        ScheduleStatus(int value) {
            this.value = value;
        }
        
        public int getValue() {
            return value;
        }
    }
    
    /**
     * 通用字典
     *
     * @author daniel.yu
	 * @date 2019年6月23日 上午11:07:36
     */
    public enum MacroType {
    	
    	/**
    	 * 类型
    	 */
    	TYPE(0),
    	
    	/**
    	 * 参数
    	 */
    	PARAM(1);
    	
    	private int value;
    	
    	MacroType(int value) {
            this.value = value;
        }
        
        public int getValue() {
            return value;
        }
    	
    }
    
    /**
     * 通用变量，表示可用、禁用、显示、隐藏
     *
     * @author daniel.yu
	 * @date 2019年6月23日 上午11:07:36
     */
    public enum StatusType {
    	
    	/**
    	 * 禁用，隐藏
    	 */
    	DISABLE(0),
    	
    	/**
    	 * 可用，显示
    	 */
    	ENABLE(1),
    	
    	/**
    	 * 显示
    	 */
    	SHOW(1),
    	
    	/**
    	 * 隐藏
    	 */
    	HIDDEN(0);
    	
    	private int value;
    	
    	StatusType(int value) {
            this.value = value;
        }
        
        public int getValue() {
            return value;
        }
    	
    }

}
