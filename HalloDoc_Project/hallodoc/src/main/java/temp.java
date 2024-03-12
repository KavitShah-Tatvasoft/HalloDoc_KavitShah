import com.password4j.BcryptFunction;
import com.password4j.Hash;
import com.password4j.Password;
import com.password4j.types.Bcrypt;

public class temp {
	public static void main(String args[]) {
		// TODO Auto-generated method stub
		BcryptFunction bcrypt = BcryptFunction.getInstance(Bcrypt.B, 12);

		Hash hash = Password.hash("kavit123@29")
                .with(bcrypt);
		
//		Hash hash1 = Password.hash("kavit")
//                .addPepper("shared-secret")
//                .with(bcrypt);
		
		boolean verified = Password.check("kavit123@29", "$2b$12$LIrwy9sSt2pfsvw2.Z64LeMDJmYL7QG7EoNBJkpit4PoFgTI5zAIi")
                .with(bcrypt);
		
//		boolean verified1 = Password.check("my password", "$2b$12$.z6oEtf4KGlPk9y4uzEsKuF.4MfAv9NQCrqXQevjYy0DMvVXZWcK2")
//                .addPepper("shared-secret")
//                .with(bcrypt);

		System.out.println(hash.getResult());
//		System.out.println(hash1.getResult());
		System.out.println(verified);
//		System.out.println(verified1);
		
		// $2b$12$.z6oEtf4KGlPk9y4uzEsKuF.4MfAv9NQCrqXQevjYy0DMvVXZWcK2

	}
	
}
