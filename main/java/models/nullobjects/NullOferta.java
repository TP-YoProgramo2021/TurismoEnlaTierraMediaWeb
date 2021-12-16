package models.nullobjects;

import java.util.List;

import model.Atraccion;
import model.Ofertables;
import model.TipoDeAtraccion;

public class NullOferta implements Ofertables {

	public static Ofertables build() {
		return new NullOferta();
	}
	
	public NullOferta() {
		super();
	}
	
	public boolean isNull() {
		return true;
	}

	@Override
	public int getCosto() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getTiempo() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public TipoDeAtraccion getTipo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hayCupo() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean esPromocion() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Atraccion> atraccionesIncluidas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String atrIncluidas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean esOContiene(Ofertables oferta) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getNombre() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int enStock() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
