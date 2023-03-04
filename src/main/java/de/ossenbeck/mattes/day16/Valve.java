package de.ossenbeck.mattes.day16;

import java.util.List;

public class Valve
{
	private final String label;
	private final int flowRate;
	private List<Valve> tunnels;

	public Valve(String label, int flowRate)
	{
		this.label = label;
		this.flowRate = flowRate;
	}

	public boolean isIntact()
	{
		return this.flowRate > 0;
	}

	public String getLabel()
	{
		return this.label;
	}

	public int getFlowRate()
	{
		return this.flowRate;
	}

	public List<Valve> getTunnels()
	{
		return this.tunnels;
	}

	public Valve setTunnels(List<Valve> tunnels)
	{
		this.tunnels = tunnels;
		return this;
	}

	@Override
	public String toString()
	{
		return label;
	}
}