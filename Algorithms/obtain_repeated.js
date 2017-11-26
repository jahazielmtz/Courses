function obtainRepeated(...numbers) {
	let obj = numbers.map((num) => {
		return {number: num, count: 1};
	}).reduce((acum, element) => {
		acum[element.number] = (acum[element.number] || 0) + element.count;
		return acum;
	}, {});
	obj = Object.entries(obj).sort((a, b) => {
		return a[1] < b[1];
	});
	console.log(obj);
	return obj[0][0];
}