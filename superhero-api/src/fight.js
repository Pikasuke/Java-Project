const calculPV = hero => {
	const pv = (hero.powerstats.durability * hero.powerstats.power) / 10
	return pv
}

const calculAttack = hero => {
	let attack = (hero.powerstats.intelligence * hero.powerstats.strength) / 100
	return attack
}

const prepareHero = hero => {
	const { id, name, powerstats: { speed } } = hero
	const attack = calculAttack(hero)
	const pv = calculPV(hero)
	const heroReady = {
		id,
		name,
		attack,
		pv,
		speed,
	}
	return heroReady
}

const hasInitiative = (playerOne, playerTwo) => {
	if (playerOne.speed > playerTwo.speed) return [playerOne, playerTwo]
	return [playerTwo, playerOne]
}

const isAlive = player => (player.pv > 0 ? 1 : 0)

const playerAttack = (playerAttack, playerDefend) => {
	playerDefend.pv = playerDefend.pv - playerAttack.attack
}

const finalFight = (playerOne, playerTwo) => {
	const one = prepareHero(playerOne)
	const two = prepareHero(playerTwo)
	const [firstToAttack, secondToAttack] = hasInitiative(one, two)

	let firstToAttackAlive = 1
	let secondToAttackAlive = 1

	while (firstToAttackAlive && secondToAttackAlive) {
		playerAttack(firstToAttack, secondToAttack)
		secondToAttackAlive = isAlive(secondToAttack)
		if (secondToAttackAlive === 0) break
		playerAttack(secondToAttack, firstToAttack)
		firstToAttackAlive = isAlive(firstToAttack)
	}
	console.log(firstToAttackAlive, secondToAttackAlive)
	if (firstToAttackAlive) {
		return firstToAttack.id === playerOne.id ? playerOne : playerTwo
	}
	return secondToAttack.id === playerOne.id ? playerOne : playerTwo
}

const getWinner = (hero1, hero2) => {
	const winner = finalFight(hero1, hero2)
	console.log(winner)
	return winner
}

module.exports = getWinner