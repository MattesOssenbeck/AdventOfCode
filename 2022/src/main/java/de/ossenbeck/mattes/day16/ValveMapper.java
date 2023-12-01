package de.ossenbeck.mattes.day16;

import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.collection.List;
import io.vavr.collection.Map;
import io.vavr.collection.Stream;
import io.vavr.control.Option;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValveMapper
{
	private static final Pattern VALVE_PATTERN = Pattern.compile("([A-Z]{2}).+=(\\d+).+ valves? (.*)");

	public static List<Valve> map(List<String> report)
	{
		var x = report.map(VALVE_PATTERN::matcher)
				.map(ValveMapper::match);
		var map = x.toMap(valveTuple2 -> valveTuple2._1.getLabel(), valveTuple2 -> valveTuple2._1);
		return x.map(t -> connectValves(t._1, t._2, map));
	}

	private static Valve connectValves(Valve valve, String[] strings, Map<String, Valve> map)
	{
		var tunnels = Stream.of(strings).map(String::trim).map(map::get).map(Option::get).toList();
		return valve.setTunnels(tunnels.toJavaList());
	}

	private static Tuple2<Valve, String[]> match(Matcher singleReportMatch)
	{
		singleReportMatch.find();
		var label = singleReportMatch.group(1);
		var flowRate = Integer.parseInt(singleReportMatch.group(2));
		var tunnels = singleReportMatch.group(3).split(",");
		return Tuple.of(new Valve(label, flowRate), tunnels);
	}

}
