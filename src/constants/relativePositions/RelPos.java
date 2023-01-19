package constants.relativePositions;

public enum RelPos {

    LEFT, // if P is on the left respect to r
    RIGHT, // if P is on the right respect to r
    BEHIND, // if P in on r on the prolungation of A
    BEYOND, // if P in on r on the prolungation of B
    BETWEEN, // if P is into AB
    ORIGIN,  // P == A
    DESTINATION; // P == B
}



