"""
test_battle.py: Testing battle.py file
"""
import unittest
from tester_base import TesterBase, captured_output

__author__ = 'Ow Yong Chee Hao and Ying Qi'

class TestBattle(TesterBase):
    def test_set_mode_battle(self):
        from battle import Battle

        try:  # Try creating a battle
            a = Battle("Joe", "Gorge")
        except Exception as e:
            self.verificationErrors.append(f"Battle could not be instantiated: {str(e)}.")
            return

        try:  # Calling set_mode_battle and assign both team with 0 pokemon
            with captured_output("0 0 0\n0 0 0") as (inp, out, err):
                result = a.set_mode_battle()
        except Exception as e:
            self.verificationErrors.append(f"Battle failed to execute: {str(e)}.")
            return

        try:  # Test if the result is draw
            assert result == "Draw"
        except AssertionError:
            self.verificationErrors.append(f"The result should be draw: {result}.")

    def test_rotating_mode_battle(self):
        from battle import Battle
        try:  # Try creating a battle
            b = Battle("Bob", "Gary")
        except Exception as e:
            self.verificationErrors.append(f"Battle could not be instantiated: {str(e)}.")
            return
        try:  # Calling set_mode_battle and assign both team with pokemons
            with captured_output("3 0 0\n0 2 2") as (inp, out, err):
                result = b.rotating_mode_battle()
        except Exception as e:
            self.verificationErrors.append(f"Battle failed to execute: {str(e)}.")
            return
        try:    # Test if winner is Gary
            assert result == "Gary"
        except AssertionError:
            self.verificationErrors.append(f"Gary should win: {result}.")
        try:    # Test the stats and sequence of remaining pokemons
            assert str(b.team2) == "Squirtle's HP = 6.0 and level = 2, Squirtle's HP = 5.0 and level = 3"
        except AssertionError:
            self.verificationErrors.append(f"Team 2 is not correct after battle: {str(b.team2)}")

    def test_optimised_mode_battle(self):
        from battle import Battle
        try:  # Try creating a battle
            c = Battle("John", "Forbe")
        except Exception as e:
            self.verificationErrors.append(f"Battle could not be instantiated: {str(e)}.")
            return
        try:  # Calling set_mode_battle and assign both team with pokemons
            with captured_output("3 1 1 1\n0 3 2") as (inp, out, err):
                result = c.optimised_mode_battle("defence", "attack")
        except Exception as e:
            self.verificationErrors.append(f"Battle failed to execute: {str(e)}.")
            return
        try:    # Test if winner is Forbe
            assert result == "Forbe"
        except AssertionError:
            self.verificationErrors.append(f"Forbe should win: {result}.")
        try: # Test the stats and sequence of remaining pokemons
            assert str(c.team2) == "Squirtle's HP = 4.0 and level = 4, Squirtle's HP = 8 and level = 1"
        except AssertionError:
            self.verificationErrors.append(f"Team 2 is not correct after battle: {str(c.team2)}")

    def test_team_battling(self):
        from array_sorted_list import ArraySortedList
        from battle import Battle
        c = Battle("John", "Forbe")
        c.battle_mode = 2  # Creating two teams for testing
        with captured_output("2 2 1"):
            c.team1.choose_team(2,"hp")
        with captured_output(("0 2 1")):
            c.team2.choose_team(2,'lvl')
        put = ArraySortedList.add
        take = ArraySortedList.delete_at_index
        try:  # Try calling team_battling method
            c.team_battling(take,put)
        except Exception as e:
            self.verificationErrors.append(f"Team_batting method failed: {str(e)}")

        try:  # Check if team1 is correct after team battling
            assert str(c.team1) =="Bulbasaur's HP = 6 and level = 1, Bulbasaur's HP = 5.0 and level = 2, Squirtle's HP = 2 and level = 1"
        except AssertionError:
            self.verificationErrors.append(f"Team_batting method is incorrect: {str(c.team1)}")

    def test_battle_instructions(self):
        from pokemon import Charmander, Squirtle
        from battle import Battle
        c = Battle("John","Forbe")
        pokemon1 = Charmander()  # Creating two pokemons for testing
        pokemon2 = Squirtle()

        try:  # Test if battle_instructions method is executable
            c.battle_instructions(pokemon1,pokemon2)
        except Exception as e:
            self.verificationErrors.append(f"battle instructions method failed: {str(e)}")

        try:  # test if pokemon1 stats is correct after battling
            assert str(pokemon1) == "Charmander's HP = 0 and level = 1"
        except AssertionError:
            self.verificationErrors.append(f"pokemon1 stats is changed: {str(pokemon1)}")

        try:  # test if pokemon2 stats is correct after battling
            assert str(pokemon2) == "Squirtle's HP = 7.0 and level = 2"
        except AssertionError:
            self.verificationErrors.append(f"pokemon2 stats is incorrect: {str(pokemon2)}")

    def test_winner(self):
        from battle import Battle
        c = Battle("John","Forbe")

        with captured_output("0 0 0"):  # Assign John empty team
            c.team1.choose_team(0)
        with captured_output("0 0 1"):  # Assign Forbe non-empty team
            c.team2.choose_team(0)

        try:  # Test if winner function is excutable
            x = c.winner()
        except Exception as e:
            self.verificationErrors.append(f"Winner function failed: {str(e)}")

        try:  # Test if winner is corredct
            assert x == "Forbe"
        except AssertionError:
            self.verificationErrors.append(f"Winner is incorrect: {str(x)}")

    def test_team_choosing(self):
        from battle import Battle
        c = Battle("John", "Forbe")
        c.battle_mode = 0
        try:  # Test if team_choosing function is executable
            with captured_output("0 0 1\n1 1 0"):
                c.team_choosing()
        except Exception as e:
            self.verificationErrors.append(f"team_choosing function failed: {str(e)}")

        try:  # test if team1 is assigned correctly
            assert str(c.team1) == "Squirtle's HP = 8 and level = 1"
        except AssertionError:
            self.verificationErrors.append(f"Team 1 not assigned correctly: {str(c.team1)}")

        try:  # test if team2 is assigned correctly
            assert str(c.team2) == "Charmander's HP = 7 and level = 1, Bulbasaur's HP = 9 and level = 1"
        except AssertionError:
            self.verificationErrors.append(f"Team 2 not assigned correctly: {str(c.team2)}")

    def test_attack(self):
        from pokemon import Charmander, Squirtle
        from battle import Battle
        c = Battle("John","Forbe")
        attacker = Charmander()  # creating two pokemons for testing
        defender = Squirtle()
        try:  # Test if attack method is excuteable
            c.attack(attacker,defender)
        except Exception as e:
            self.verificationErrors.append(f"attack method failed: {str(e)}")

        try:  # Test if attacker stats is changed after attacking
            assert str(attacker) == "Charmander's HP = 7 and level = 1"
        except AssertionError:
            self.verificationErrors.append(f"Attacker stats is changed: {str(attacker)}")

        try:  # Test if defender stats is correct after being attacked
            assert str(defender) == "Squirtle's HP = 7.0 and level = 1"
        except AssertionError:
            self.verificationErrors.append(f"Defender stats is incorrect: {str(defender)}")



if __name__ == '__main__':
    suite = unittest.TestLoader().loadTestsFromTestCase(TestBattle)
    unittest.TextTestRunner(verbosity=0).run(suite)