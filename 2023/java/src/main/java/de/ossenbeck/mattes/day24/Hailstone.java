package de.ossenbeck.mattes.day24;

public record Hailstone(long px, long py, long pz, long vx, long vy, long vz) {
    /*
     * https://stackoverflow.com/a/2932601
     * Treating the trajectory of each hailstone as a ray with a starting point up to infinity
     */
    public boolean intersectsInAreaWith(long lower, long upper, Hailstone other) {
        var dx = other.px - this.px;
        var dy = other.py - this.py;
        var det = other.vx * this.vy - other.vy * this.vx;
        if (det == 0) {
            return false;
        }
        var t1 = (dy * other.vx - dx * other.vy) / det;
        var t2 = (dy * this.vx - dx * this.vy) / det;
        if (t1 < 0 || t2 < 0) {
            return false;
        }
        var intersectionX = this.px + this.vx * t1;
        var intersectionY = this.py + this.vy * t1;
        return intersectionX >= lower && intersectionX <= upper
                && intersectionY >= lower && intersectionY <= upper;
    }
}