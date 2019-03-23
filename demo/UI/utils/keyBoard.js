
export default keyBoard = {
	code: null,
	lastTime: null,
	nextTime: null,
	lastCode: null,
	nextCode: null,
	keyboardEvent: (keyEvent,cb) {
		this.nextCode = keyEvent.keyCode;
		this.nextTime = new Date().getTime();
		if (this.lastCode != null && this.lastTime != null && this.nextTime - this.lastTime <= 30) {
			this.code += this.lastCode - 7;
		} else if (
			this.lastCode != null &&
			this.lastTime != null &&
			this.nextTime - this.lastTime > 100
		) {
			this.code = '';
		}
		this.lastCode = this.nextCode;
		this.lastTime = this.nextTime;
		if (keyEvent.keyCode == 66) {
			cb(this.code);
		}
	}

}
