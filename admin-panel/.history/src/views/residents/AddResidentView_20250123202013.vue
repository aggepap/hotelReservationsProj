<script setup>
import { FormKit } from "@formkit/vue";
</script>
<template>
  <h2 class="text-black text-3xl text-center leading-5 font-semibold mb-8">
    Add new Resident
  </h2>
  <div class="bg-white w-6/12 rounded-md">
    <FormKit type="form">
      <div class="flex">
        <FormKit
          type="text"
          name="firstName"
          label="First Name"
          validation="required | length:3,20"
          validation-visibility="blur"
        />
        <FormKit
          type="text"
          name="lastname"
          label="Last Name"
          validation="required | length:3,30"
          validation-visibility="blur"
        />
      </div>
      <div class="flex">
        <FormKit
          type="number"
          name="vat"
          label="VAT Number"
          validation="alphanumeric| required |length:10,10"
          validation-visibility="blur"
        />
        <FormKit
          type="text"
          name="idNumber"
          label="ID Number"
          validation="alphanumeric| required |length:6,15"
          validation-visibility="blur"
        />
      </div>
      <FormKit
        type="select"
        label="Country"
        name="country"
        validation="required"
        validation-visibility="blur"
        :options="{
          AFG: 'Afghanistan',
          ALB: 'Albania',
          DZA: 'Algeria',
          ASM: 'American Samoa',
          AND: 'Andorra',
          AGO: 'Angola',
          AIA: 'Anguilla',
          ATG: 'Antigua and Barbuda',
          ARG: 'Argentina',
          ARM: 'Armenia',
          AUS: 'Australia',
          AUT: 'Austria',
          AZE: 'Azerbaijan',
          BHS: 'Bahamas',
          BHR: 'Bahrain',
          BGD: 'Bangladesh',
          BRB: 'Barbados',
          BLR: 'Belarus',
          BEL: 'Belgium',
          BLZ: 'Belize',
          BEN: 'Benin',
          BMU: 'Bermuda',
          BTN: 'Bhutan',
          BOL: 'Bolivia',
          BIH: 'Bosnia and Herzegovina',
          BWA: 'Botswana',
          BRA: 'Brazil',
          BRN: 'Brunei Darussalam',
          BGR: 'Bulgaria',
          BFA: 'Burkina Faso',
          BDI: 'Burundi',
          KHM: 'Cambodia',
          CMR: 'Cameroon',
          CAN: 'Canada',
          CPV: 'Cape Verde',
          CAF: 'Central African Republic',
          TCD: 'Chad',
          CHL: 'Chile',
          CHN: 'China',
          COL: 'Colombia',
          COM: 'Comoros',
          COG: 'Congo',
          CRI: 'Costa Rica',
          HRV: 'Croatia',
          CUB: 'Cuba',
          CYP: 'Cyprus',
          CZE: 'Czech Republic',
          COD: 'Democratic Republic of the Congo',
          DNK: 'Denmark',
          DJI: 'Djibouti',
          DMA: 'Dominica',
          DOM: 'Dominican Republic',
          ECU: 'Ecuador',
          EGY: 'Egypt',
          SLV: 'El Salvador',
          GNQ: 'Equatorial Guinea',
          ERI: 'Eritrea',
          EST: 'Estonia',
          ETH: 'Ethiopia',
          FJI: 'Fiji',
          FIN: 'Finland',
          FRA: 'France',
          GAB: 'Gabon',
          GMB: 'Gambia',
          GEO: 'Georgia',
          DEU: 'Germany',
          GHA: 'Ghana',
          GRC: 'Greece',
          GRL: 'Greenland',
          GRD: 'Grenada',
          GTM: 'Guatemala',
          GIN: 'Guinea',
          GNB: 'Guinea-Bissau',
          GUY: 'Guyana',
          HTI: 'Haiti',
          HND: 'Honduras',
          HKG: 'Hong Kong',
          HUN: 'Hungary',
          ISL: 'Iceland',
          IND: 'India',
          IDN: 'Indonesia',
          IRN: 'Iran',
          IRQ: 'Iraq',
          IRL: 'Ireland',
          ISR: 'Israel',
          ITA: 'Italy',
          JAM: 'Jamaica',
          JPN: 'Japan',
          JOR: 'Jordan',
          KAZ: 'Kazakhstan',
          KEN: 'Kenya',
          KIR: 'Kiribati',
          PRK: 'North Korea',
          KOR: 'South Korea',
          KWT: 'Kuwait',
          KGZ: 'Kyrgyzstan',
          LAO: 'Laos',
          LVA: 'Latvia',
          LBN: 'Lebanon',
          LBR: 'Liberia',
          LBY: 'Libya',
          LIE: 'Liechtenstein',
          LTU: 'Lithuania',
          LUX: 'Luxembourg',
          MAC: 'Macao',
          MDG: 'Madagascar',
          MWI: 'Malawi',
          MYS: 'Malaysia',
          MDV: 'Maldives',
          MLI: 'Mali',
          MLT: 'Malta',
          MHL: 'Marshall Islands',
          MTQ: 'Martinique',
          MRT: 'Mauritania',
          MUS: 'Mauritius',
          MEX: 'Mexico',
          FSM: 'Micronesia',
          MDA: 'Moldova',
          MCO: 'Monaco',
          MNG: 'Mongolia',
          MNE: 'Montenegro',
          MAR: 'Morocco',
          MOZ: 'Mozambique',
          MMR: 'Myanmar',
          NAM: 'Namibia',
          NRU: 'Nauru',
          NPL: 'Nepal',
          NLD: 'Netherlands',
          NZL: 'New Zealand',
          NIC: 'Nicaragua',
          NER: 'Niger',
          NGA: 'Nigeria',
          NOR: 'Norway',
          OMN: 'Oman',
          PAK: 'Pakistan',
          PLW: 'Palau',
          PSE: 'Palestine, State of',
          PAN: 'Panama',
          PNG: 'Papua New Guinea',
          PRY: 'Paraguay',
          PER: 'Peru',
          PHL: 'Philippines',
          POL: 'Poland',
          PRT: 'Portugal',
          QAT: 'Qatar',
          ROU: 'Romania',
          RUS: 'Russia',
          RWA: 'Rwanda',
          KNA: 'Saint Kitts and Nevis',
          LCA: 'Saint Lucia',
          VCT: 'Saint Vincent and the Grenadines',
          WSM: 'Samoa',
          SMR: 'San Marino',
          STP: 'Sao Tome and Principe',
          SAU: 'Saudi Arabia',
          SEN: 'Senegal',
          SRB: 'Serbia',
          SYC: 'Seychelles',
          SLE: 'Sierra Leone',
          SGP: 'Singapore',
          SVK: 'Slovakia',
          SVN: 'Slovenia',
          SLB: 'Solomon Islands',
          SOM: 'Somalia',
          ZAF: 'South Africa',
          SSD: 'South Sudan',
          ESP: 'Spain',
          LKA: 'Sri Lanka',
          SDN: 'Sudan',
          SUR: 'Suriname',
          SWE: 'Sweden',
          CHE: 'Switzerland',
          SYR: 'Syria',
          TWN: 'Taiwan',
          TJK: 'Tajikistan',
          TZA: 'Tanzania',
          THA: 'Thailand',
          TLS: 'Timor-Leste',
          TGO: 'Togo',
          TON: 'Tonga',
          TTO: 'Trinidad and Tobago',
          TUN: 'Tunisia',
          TUR: 'Turkey',
          TKM: 'Turkmenistan',
          TUV: 'Tuvalu',
          UGA: 'Uganda',
          UKR: 'Ukraine',
          ARE: 'United Arab Emirates',
          GBR: 'United Kingdom',
          USA: 'United States',
          URY: 'Uruguay',
          UZB: 'Uzbekistan',
          VUT: 'Vanuatu',
          VEN: 'Venezuela',
          VNM: 'Vietnam',
          YEM: 'Yemen',
          ZMB: 'Zambia',
          ZWE: 'Zimbabwe',
        }"
      />
      <FormKit
        type="date"
        value="1999-01-01"
        label="Date of Birth"
        help="Enter date of birth"
        validation="required"
        validation-visibility="blur"
      />
      <FormKit
        type="select"
        label="Gender"
        name="gender"
        validation="required"
        validation-visibility="blur"
        :options="{
          male: 'Male',
          female: 'Female',
          other: 'Other',
        }"
      />
      <FormKit
        type="email"
        label="Resident's email address"
        help="Please enter Resident's email address."
        validation="email"
        validation-visibility="live"
        placeholder="email@example.com"
      />
    </FormKit>
  </div>
</template>
