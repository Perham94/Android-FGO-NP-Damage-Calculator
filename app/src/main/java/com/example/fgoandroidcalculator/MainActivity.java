package com.example.fgoandroidcalculator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


import android.widget.EditText;
import android.widget.Spinner;

import java.math.BigInteger;

public class MainActivity extends AppCompatActivity {
	public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}


	public String calculator(EditText serventAtk, EditText npDmg, EditText cardBuff, EditText npbuff,
						   EditText atkBuff, EditText specialTrait, Spinner servantClass,
						   Spinner classAdvantage, Spinner attrAdvantage, Spinner npType) {


		float servantAtk = Float.parseFloat(serventAtk.getText().toString());
		;
		float npDamageMultiplier = Float.parseFloat(npDmg.getText().toString());
		;
		float firstCardBonus = 0f;
		float cardDamageValue = 1.5f;
		float cardMod = Float.parseFloat(cardBuff.getText().toString())/100;
		float classAtkBonus = 1.1f;
		float triangleModifier = 1f;
		float attributeModifier = 1f;
		float randomModifier = 0.9f;
		float randomModifierSecound = 1.1f;
		float atkMod = Float.parseFloat(atkBuff.getText().toString())/100;
		float defMod = 0f;
		float criticalModifier = 1f;
		float extraCardModifier = 1f;
		float specialDefMod = 0f;
		float powerMod = Float.parseFloat(specialTrait.getText().toString())/100;
		float selfDamageMod = 0f;
		float critDamageMod = 0.1f;
		float isCrit = 0f;
		float npDamageMod = Float.parseFloat(npbuff.getText().toString())/100;
		float isNP = 1f;
		float superEffectiveModifier = 1f;
		float isSuperEffective = 1f;
		float dmgPlusAdd = 1.2f;
		float selfDmgCutAdd = 0f;
		float busterChainMod = 0f;

		if (classAdvantage.getSelectedItem().toString().equals("Yes")) {
			triangleModifier = 2f;
		}
		if (classAdvantage.getSelectedItem().toString().equals("No")) {
			triangleModifier = 0.5f;
		}
		if (classAdvantage.getSelectedItem().toString().equals("Neutral")) {
			triangleModifier = 1f;
		}
		if (classAdvantage.getSelectedItem().toString().equals("Berserker")) {
			triangleModifier = 1.5f;
		}

		if (attrAdvantage.getSelectedItem().toString().equals("Yes")) {
			attributeModifier = 1.1f;
		}


		if (servantClass.getSelectedItem()
				.toString().equals("Berserker") || servantClass.getSelectedItem()
				.toString().equals("Avenger") || servantClass.getSelectedItem()
				.toString().equals("Ruler")) {
			classAtkBonus = 1.1f;
		}
		if (servantClass.getSelectedItem()
				.toString().equals("Lancer")) {
			classAtkBonus = 1.05f;
		}
		if (servantClass.getSelectedItem()
				.toString().equals("Saber") || servantClass.getSelectedItem()
				.toString().equals("Rider") || servantClass.getSelectedItem()
				.toString().equals("Shield") || servantClass.getSelectedItem()
				.toString().equals("Moon_Cancer") || servantClass.getSelectedItem()
				.toString().equals("Alter_Ego") || servantClass.getSelectedItem()
				.toString().equals("Foreigner")) {
			classAtkBonus = 1f;
		}
		if (servantClass.getSelectedItem()
				.toString().equals("Archer")) {
			classAtkBonus = 0.95f;
		}
		if (servantClass.getSelectedItem()
				.toString().equals("Caster") || servantClass.getSelectedItem()
				.toString().equals("Assassin")) {
			classAtkBonus = 0.9f;
		}
		if (npType.getSelectedItem().toString().equals("Buster")) {
			cardDamageValue = 1.5f;
		}
		if (npType.getSelectedItem().toString().equals("Quick")) {
			cardDamageValue = 0.8f;
		}
		if (npType.getSelectedItem().toString().equals("Art")) {
			cardDamageValue = 1f;
		}

		double damage = (servantAtk * npDamageMultiplier * (firstCardBonus + (cardDamageValue * (1 + cardMod)))
				* classAtkBonus * triangleModifier * attributeModifier * randomModifier * 0.23 * (1 + atkMod - defMod)
				* criticalModifier * extraCardModifier * (1 - specialDefMod)
				* (1 + powerMod + selfDamageMod + (critDamageMod * isCrit) + (npDamageMod * isNP))
				* (1 + ((superEffectiveModifier - 1) * isSuperEffective))) + dmgPlusAdd + selfDmgCutAdd
				+ (servantAtk * busterChainMod);

		double damage_secound = (servantAtk * npDamageMultiplier * (firstCardBonus + (cardDamageValue * (1 + cardMod)))
				* classAtkBonus * triangleModifier * attributeModifier * randomModifierSecound * 0.23 * (1 + atkMod - defMod)
				* criticalModifier * extraCardModifier * (1 - specialDefMod)
				* (1 + powerMod + selfDamageMod + (critDamageMod * isCrit) + (npDamageMod * isNP))
				* (1 + ((superEffectiveModifier - 1) * isSuperEffective))) + dmgPlusAdd + selfDmgCutAdd
				+ (servantAtk * busterChainMod);


		String result = String.valueOf((damage+damage_secound)/2);

		return result;
	}


	public void sendMessage(View view) {
		Intent intent = new Intent(this, DisplayMessageActivity.class);

		EditText serventAtk = (EditText) findViewById(R.id.servantAtk);
		EditText npbuff = (EditText) findViewById(R.id.editTextNpBuff);
		EditText cardBuff = (EditText) findViewById(R.id.editTextCardMod);
		EditText npDmg = (EditText) findViewById(R.id.editTextNpDmgMulti);
		EditText atkBuff = (EditText) findViewById(R.id.editTextAtkBuff);
		EditText specialTrait = (EditText) findViewById(R.id.editTextSpecialTraitBuff);


		Spinner servantClass = findViewById(R.id.spinnerClass);
		Spinner classAdvantage = findViewById(R.id.spinnerClassAdvantage);
		Spinner attrAdvantage = findViewById(R.id.spinnerAttrAdvantage);
		Spinner npType = findViewById(R.id.spinnerNpTyp);


		String message = calculator(serventAtk,npDmg,cardBuff,npbuff,
				atkBuff,  specialTrait,servantClass,
				classAdvantage, attrAdvantage,npType);

		intent.putExtra(EXTRA_MESSAGE, message);
		startActivity(intent);
	}


}
