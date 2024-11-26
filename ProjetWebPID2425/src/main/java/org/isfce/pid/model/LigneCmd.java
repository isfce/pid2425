package
org.isfce.pid.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class LigneCmd {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer num;
	@ManyToOne()
	@JoinColumn(name = "FKCommande", nullable = false)
	private Commande cmd;
	private String article;
	private int qt;
	
	public LigneCmd(Commande cmd, String article, int qt) {
		super();
		this.cmd = cmd;
		this.article = article;
		this.qt = qt;
	}
	
	
}
