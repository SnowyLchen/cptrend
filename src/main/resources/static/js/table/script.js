const info = {
    data: {
        consonants: ['ᄀ', 'ᄂ', 'ᄃ', 'ᄅ', 'ᄆ', 'ᄇ', 'ᄉ', 'ᄋ', 'ᄌ', 'ᄎ', 'ᄏ', 'ᄐ', 'ᄑ', 'ᄒ'],
        vowels: ['ㅏ', 'ᅣ', 'ᅥ', 'ᅧ', 'ᅩ', 'ᅭ', 'ᅮ', 'ᅲ', 'ᅳ', 'ᅵ']
    },

    computed: {
        characters() {
            return [...this.vowels, ...this.combos];
        },
        combos() {
            return this.consonants.map(consonant => {
                return [consonant, ...this.vowels.map(vowel => {
                    return `${consonant}${vowel}`;
                })];
            }).flat();
        }
    }
};


const app = new Vue(info);
app.$mount('#app');