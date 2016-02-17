package inference;

/**
 * @purpose 对象创建工厂
 * @author zhaohongjie
 * 
 */
public class ReasonerFactory {

	public static IReasoner createFamilyReasoner() {
		IReasoner familyReasoner = new ReasonerImpl();
		return familyReasoner;
	}
}
