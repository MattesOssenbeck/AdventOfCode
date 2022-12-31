package de.ossenbeck.mattes.day13;

import de.ossenbeck.mattes.SolveableTest;
import io.vavr.collection.List;

class DecoderTest extends SolveableTest<Integer>
{
	private static final List<PacketPair> PACKET_PAIRS = PacketMapper.map("""
			[1,1,3,1,1]
			[1,1,5,1,1]

			[[1],[2,3,4]]
			[[1],4]

			[9]
			[[8,7,6]]

			[[4,4],4,4]
			[[4,4],4,4,4]

			[7,7,7,7]
			[7,7,7]

			[]
			[3]

			[[[]]]
			[[]]

			[1,[2,[3,[4,[5,6,7]]]],8,9]
			[1,[2,[3,[4,[5,6,0]]]],8,9]""");

	public DecoderTest()
	{
		super(new Decoder(PACKET_PAIRS));
	}

	@Override
	protected Integer getExpectedResultPartOne()
	{
		return 13;
	}

	@Override
	protected Integer getExpectedResultPartTwo()
	{
		return 140;
	}
}