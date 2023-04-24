package com.majid2851.hero_datasource.cache

import com.majid2851.hero_datasource.network.EndPoints
import com.majid2851.hero_domain.Hero
import com.majid2851.hero_domain.HeroAttribute
import com.majid2851.hero_domain.HeroAttribute.Agility.getHeroAttrFromAbbreviation
import com.majid2851.hero_domain.HeroRole
import com.majid2851.hero_domain.getHeroAttackType
import com.majid2851.hero_domain.getHeroRole
import com.majid2851.herodatasource.cache.Hero_Entity

fun Hero_Entity.toHero(): Hero {
    return Hero(
        id = id.toInt(),
        localizedName = localizedName,
        primaryAttribute = getHeroAttrFromAbbreviation(primaryAttribute),
        attackType = getHeroAttackType(attackType),
        roles = rolesToList(
            carry = roleCarry?.toInt() == 1,
            escape = roleEscape?.toInt() == 1,
            nuker = roleNuker?.toInt() == 1,
            initiator = roleInitiator?.toInt() == 1,
            durable = roleDurable?.toInt() == 1,
            disabler = roleDisabler?.toInt() == 1,
            jungler = roleJungler?.toInt() == 1,
            support = roleSupport?.toInt() == 1,
            pusher = rolePusher?.toInt() == 1,
        ),
        img = img,
        icon = icon,
        baseHealth = baseHealth.toFloat(),
        baseHealthRegen = baseHealthRegen?.toFloat(),
        baseMana = baseMana.toFloat(),
        baseManaRegen = baseManaRegen?.toFloat(),
        baseArmor = baseArmor.toFloat(),
        baseMoveRate = baseMoveRate.toFloat(),
        baseAttackMin = baseAttackMin.toInt(),
        baseAttackMax = baseAttackMax.toInt(),
        baseStr = baseStr.toInt(),
        baseAgi = baseAgi.toInt(),
        baseInt = baseInt.toInt(),
        strGain = strGain.toFloat(),
        agiGain = agiGain.toFloat(),
        intGain = intGain.toFloat(),
        attackRange = attackRange.toInt(),
        projectileSpeed = projectileSpeed.toInt(),
        attackRate = attackRate.toFloat(),
        moveSpeed = moveSpeed.toInt(),
        turnRate = turnRate?.toFloat(),
        legs = legs.toInt(),
        turboPicks = turboPicks.toInt(),
        turboWins = turboWins.toInt(),
        proWins = proWins.toInt(),
        proPick = proPick.toInt(),
        firstPick = firstPick.toInt(),
        firstWin = firstWin.toInt(),
        secondPick = secondPick.toInt(),
        secondWin = secondWin.toInt(),
        thirdPick = thirdPick.toInt(),
        thirdWin = thirdWin.toInt(),
        fourthPick = fourthPick.toInt(),
        fourthWin = fourthWin.toInt(),
        fifthPick = fifthPick.toInt(),
        fifthWin = fifthWin.toInt(),
        sixthPick = sixthPick.toInt(),
        sixthWin = sixthWin.toInt(),
        seventhPick = seventhPick.toInt(),
        seventhWin = seventhWin.toInt(),
        eighthWin = eighthWin.toInt(),
        eighthPick = eighthPick.toInt(),
    )
}

fun rolesToList(
    carry: Boolean,
    escape: Boolean,
    nuker: Boolean,
    initiator: Boolean,
    durable: Boolean,
    disabler: Boolean,
    jungler: Boolean,
    support: Boolean,
    pusher: Boolean,
): List<HeroRole>{
    val roles: MutableList<HeroRole> = mutableListOf()
    if(carry) roles.add(HeroRole.Carry)
    if(escape) roles.add(HeroRole.Escape)
    if(nuker) roles.add(HeroRole.Nuker)
    if(initiator) roles.add(HeroRole.Initiator)
    if(durable) roles.add(HeroRole.Durable)
    if(disabler) roles.add(HeroRole.Disabler)
    if(jungler) roles.add(HeroRole.Jungler)
    if(support) roles.add(HeroRole.Support)
    if(pusher) roles.add(HeroRole.Pusher)
    return roles.toList()
}