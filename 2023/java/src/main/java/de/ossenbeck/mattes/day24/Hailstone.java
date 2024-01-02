package de.ossenbeck.mattes.day24;

import java.util.Optional;

public record Hailstone(long px, long py, long pz, long vx, long vy, long vz) {
    /*
     * https://stackoverflow.com/a/2932601
     * Treating the trajectory of each hailstone as a ray with a starting point up to infinity
     */
    public Optional<DoubleCoordinate> calculateIntersection(Hailstone other) {
        var det = other.vx * this.vy - other.vy * this.vx;
        if (det == 0) {
            if (this.px == other.px && this.py == other.py) {
                return Optional.empty();
            }
            var t = this.vx == 0 ? 0 : (other.px - this.px) / this.vx;
            if (t > 0) {
                return other.hitsAfterT(this, t);
            }
            t = other.vx == 0 ? 0 : (this.px - other.px) / other.vx;
            if (t > 0) {
                return this.hitsAfterT(other, t);
            }
            return Optional.empty();
        }
        var dx = other.px - this.px;
        var dy = other.py - this.py;
        var t1 = (double) (dy * other.vx - dx * other.vy) / det;
        var t2 = (double) (dy * this.vx - dx * this.vy) / det;
        var intersectionX = this.px + this.vx * t1;
        var intersectionY = this.py + this.vy * t1;
        return Optional.of(new DoubleCoordinate(intersectionX, intersectionY))
                .filter(__ -> t1 >= 0 && t2 >= 0);
    }

    public long x(long t) {
        return px + t * vx;
    }

    public long y(long t) {
        return py + t * vy;
    }

    public long z(long t) {
        return pz + t * vz;
    }

    public Hailstone substractDeltaVelocity(long dvx, long dvy) {
        return new Hailstone(px, py, pz, vx - dvx, vy - dvy, vz);
    }

    private Optional<DoubleCoordinate> hitsAfterT(Hailstone other, long t) {
        return Optional.of(new DoubleCoordinate(other.x(t), other.y(t)))
                .filter(__ -> other.x(t) == this.px && other.y(t) == this.py);
    }

    public long calculateTimeOfIntersection(Hailstone hailstone) {
        return this.calculateIntersection(hailstone)
                .map(DoubleCoordinate::x)
                .map(Double::longValue)
                .map(intersectionX -> intersectionX - this.px())
                .map(distanceX -> Math.divideExact(distanceX, this.vx())).orElseThrow();
    }
}