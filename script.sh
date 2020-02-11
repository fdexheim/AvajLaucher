#!/bin/sh
echo "[---JAVAC---]"
find . -name "*.java" > sources.txt
javac -sourcepath "./" @sources.txt

chmod 644 Simulation.txt

echo "[---TEST BAD AIRCRAFT---]"
java src.com.fdexheim.Simulator scenarios/scenarioBadAircraft.txt
echo "--Simulation.txt--"
cat Simulation.txt
echo ""

echo "[---TEST BAD ARGS---]"
java src.com.fdexheim.Simulator scenarios/scenarioBadArgs.txt
echo "--Simulation.txt--"
cat Simulation.txt
echo ""

echo "[---TEST BAD COORDINATES---]"
java src.com.fdexheim.Simulator scenarios/scenarioBadCoordinates.txt
echo "--Simulation.txt--"
cat Simulation.txt
echo ""

echo "[---TEST BAD ITER---]"
java src.com.fdexheim.Simulator scenarios/scenarioBadIter.txt
echo "--Simulation.txt--"
cat Simulation.txt
echo ""

echo "[---TEST BAD ITER 2---]"
java src.com.fdexheim.Simulator scenarios/scenarioBadIter2.txt
echo "--Simulation.txt--"
cat Simulation.txt
echo ""

echo "[---TEST BAD ITER 2---]"
java src.com.fdexheim.Simulator scenarios/scenarioBadIter2.txt
echo "--Simulation.txt--"
cat Simulation.txt
echo ""

echo "[---TEST SIMULATION FILE ACCESS---]"
chmod 444 Simulation.txt
java src.com.fdexheim.Simulator scenarios/scenarioB.txt
chmod 644 Simulation.txt
echo "--Simulation.txt--"
cat Simulation.txt
echo ""

echo "[---TEST SCENARIO FILE ACCESS---]"
chmod 000 scenarios/scenarioB.txt
java src.com.fdexheim.Simulator scenarios/scenarioB.txt
chmod 755 scenarios/scenarioB.txt
echo "--Simulation.txt--"
cat Simulation.txt
echo ""

echo "[---TEST NOT ENOUGH ARGS---]"
java src.com.fdexheim.Simulator
echo "--Simulation.txt--"
cat Simulation.txt
echo ""

echo "[---TEST MANY ARGS---]"
java src.com.fdexheim.Simulator scenarios/scenarioB.txt scenarios/scenario.txt
echo "--Simulation.txt--"
cat Simulation.txt
echo ""

echo "[---TEST NORMAL---]"
java src.com.fdexheim.Simulator scenarios/scenario.txt
echo "--Simulation.txt--"
cat Simulation.txt
echo ""
