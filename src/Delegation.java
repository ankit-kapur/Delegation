public class Delegation {
	public static void main(String args[]) {
	C111 c111 = new C111();
		System.out.println("Using Inheritance-->"+c111.m111());

		C112 c112 = new C112();
		System.out.println("Using Inheritance-->"+c112.m112());

		D111 d111 = new D111();
		System.out.println("Using Delegation-->"+d111.m111());

		D112 d112 = new D112();
		System.out.println("Using Delegation-->"+d112.m112());
	}
}

class C1 {
	int a1 = 1;

	public int m1() {
		return a1 + p1(100) + q1(100);
	}

	public int p1(int m) {
		return m;
	}

	public int q1(int m) {
		return m;
	}
}

class C11 extends C1 {
	int a11 = 11;

	public int m11() {
		return m1() + q1(200);
	}

	public int p1(int m) {
		return m * a1;
	}

	public int q1(int m) {
		return m + a11;
	}
}

class C111 extends C11 {
	int a111 = 111;

	public int m111() {
		return m1() + m11() + a111;
	}

	public int p1(int m) {
		return m * a1 * a11;
	}
}

class C112 extends C11 {
	int a112 = 112;

	public int m112() {
		return m1() + m11() + a112;
	}

	public int p1(int m) {
		return m * a1 * a11 * a112;

	}
}


// -------SIMULATING CLASS INHERITANCE BY DELEGATION ---------
interface I1 {
	public int m1();
	public int p1(int m);
	public int q1(int m);

}

interface I11 extends I1 {
	public int m11();
}

interface I111 extends I11 {
	public int m111();
}

interface I112 extends I11 {
	public int m112();
}

class D1 implements I1 {
	protected int d1 = 1;
	public int getD1() {
		return d1;
	}
	I1 i;
	D1(I1 i)
	{
		this.i=i;
	}
	@Override
	public int m1() { 
		return d1 + p1(100) + q1(100);
	}

	@Override
	public int p1(int m) {
		return  this.i.p1(m);
		}
	@Override
	public int q1(int m) {
		return  this.i.q1(m);
	}
}
class D11  implements I11 {
	protected int d11 = 11;
	public int getD11() {
		return d11;
	}
	I1 i;
	D1 d1Object=null;
	D11(I1 i)
	{
		this.i=i;
		d1Object= new D1(this.i);
	}
	
	
	@Override
	public int m1() {
		return d1Object.m1();
		}
	@Override
	public int p1(int m) {
		return m * d1Object.getD1();
		} 

	@Override
	public int q1(int m) {
		return m + d11;
	}

	@Override
	public int m11() {
		return d1Object.m1() + q1(200);
		}
	}

	
class D111  implements I111 {
	protected int d111 = 111;
	public int getD111() {
		return d111;
	}
	D11 d11Object=null;
	D1 d1Object=null;
	D111()
	{
		 d11Object= new D11(this);
		 d1Object= new D1(this);
	}
	@Override
	public int p1(int m) {
		return m * d1Object.getD1() * d11Object.getD11();
	}
	@Override
	public int m111() {
		return m1() + m11() + d111;
	}

	@Override
	public int m11() {
		return d11Object.m11();
	}
	@Override
	public int m1() {
		return d11Object.m1();
	}
	@Override
	public int q1(int m) {
		return d11Object.q1(m);
	}
}
class D112  implements I112 {
	protected int d112 = 112;
	public int getD112() {
		return d112;
	}
	D11 d11=null;
	D1 d1=null;
	D112()
	{
		d11= new D11(this);
		d1= new D1(this);
	}
	
	@Override
	public int p1(int m) {
		return m * d1.getD1() * d11.getD11() * d112;
	}
	@Override
	public int m112() {
		return m1() + m11() + d112;
	}
	@Override
	public int m11() {
		return d11.m11();
	}
	@Override
	public int m1() {
		return d11.m1();
	}
	@Override
	public int q1(int m) {
		return d11.q1(m);
	}

}
